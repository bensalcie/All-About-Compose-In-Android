package bensalcie.samples.allaboutcompose_android.presentation.ui.recipe.recipelist

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bensalcie.samples.allaboutcompose_android.domain.model.Recipe
import bensalcie.samples.allaboutcompose_android.repository.RecipeRepository
import bensalcie.samples.allaboutcompose_android.utils.DEBUG_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

///Use ViewModelInject in the constructor to inject components to the viewmodel.

const val PAGE_SIZE = 30

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String
) : ViewModel() {

//Old model
    //  private  val _recipes :MutableLiveData<List<Recipe>> = MutableLiveData()
//     val recipes : LiveData<List<Recipe>> get() = _recipes

    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())

    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)

    var categoryScrollPosition: Int = 0
    var loading = mutableStateOf(false)
    var page = mutableStateOf(1)
    var recipeListScrolPosition = 0

    var query = mutableStateOf("")

    init {
        onTriggerEvent(RecipeListEvent.NewSearchEvent)
    }

    private suspend fun newSearch() {
        loading.value = true
        resetSearchState()
            delay(2000)

            val result = repository.search(token = token, page = 1, query = query.value)
            recipes.value = result
            loading.value = false


    }

    fun onQueryChange(query: String) {
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChange(category)
    }

    private fun incrementPage() {
        page.value = page.value + 1
    }


     fun onChangeRecipeScrollPosition(position: Int) {
        recipeListScrolPosition = position
    }


    //    Append new recipe list to the current recipe list.
    private fun appendRecipes(recipes: List<Recipe>) {
        val current = ArrayList(this.recipes.value)
        current.addAll(recipes)
        this.recipes.value = current
    }

    fun onTriggerEvent(event:RecipeListEvent){
        viewModelScope.launch {
            try {

                when(event){
                    is RecipeListEvent.NewSearchEvent->{
                        newSearch()

                    }

                    is RecipeListEvent.NextPageEvent->{
                        nextPage()

                    }
                }

            }catch (e:Exception){
                Log.d(DEBUG_TAG, "onTriggerEvent: $event , cause ${e.cause} ")
            }
        }
    }

   private suspend  fun nextPage() {
            //Prevent duplication due to recomposition.
            if ((recipeListScrolPosition + 1) >=  (page.value * PAGE_SIZE)) {
                loading.value = true
                incrementPage()
                Log.d(DEBUG_TAG, "nextPage triggered: page ${page.value}")
                //Just to show pagination api is fast.
                delay(1000)
                if (page.value > 1) {
                    val results =
                        repository.search(token = token, query = query.value, page = page.value)

                    Log.d(DEBUG_TAG, "nextPage: RESULTS: $results")
                    appendRecipes(results)
                }
                loading.value = false

        }
    }

    fun onChangeScrollPosition(position: Int) {
        this.categoryScrollPosition = position
    }

    private fun resetSearchState() {
        recipes.value = listOf()
        page.value = 1
        onChangeRecipeScrollPosition(0)
        if (selectedCategory.value?.value != query.value)
            clearSelectedCategory()

    }

    private fun clearSelectedCategory() {
        this.selectedCategory.value = null

    }

//    init {
//
//        Log.d("ViewModel", "random string $randomString")
//        Log.d("ViewModel", "token $token")
//        Log.d("ViewModel", "repository $repository")
//
//
//    }

    //Test
//    fun getRandomString() = randomString
//    fun getToken()= token
//    fun getRepository() = repository
}