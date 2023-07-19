package bensalcie.samples.allaboutcompose_android.presentation.ui.recipe.recipelist

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bensalcie.samples.allaboutcompose_android.domain.model.Recipe
import bensalcie.samples.allaboutcompose_android.repository.RecipeRepository
import bensalcie.samples.allaboutcompose_android.utils.DEBUG_TAG
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

///Use ViewModelInject in the constructor to inject components to the viewmodel.

const val PAGE_SIZE = 30
const val STATE_KEY_PAGE = "recipe.state.page.key"
const val STATE_KEY_QUERY ="recipe.state.query.key"
const val STATE_KEY_LIST_POSITION ="recipe.state.query.list_position"
const val STATE_KEY_SELECTED_CATEGORY ="recipe.state.query.selected_category"

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String,
      private val savedStateHandle: SavedStateHandle,
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
        savedStateHandle.get<Int>(STATE_KEY_PAGE)?.let { p->setPage(p) }
        savedStateHandle.get<String>(STATE_KEY_QUERY)?.let { q->setQuery(q) }

        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let { p->setListScrollPosition(p) }

        savedStateHandle.get<FoodCategory>(STATE_KEY_SELECTED_CATEGORY)?.let { c->setSelectedCategory(c) }

        if(recipeListScrolPosition!=0){
            onTriggerEvent(RecipeListEvent.RestoreStateEvent)

        }else{
            onTriggerEvent(RecipeListEvent.NewSearchEvent)

        }
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
        setQuery(query = query)
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        setSelectedCategory(newCategory)
        onQueryChange(category)
    }

    private fun incrementPage() {
        setPage(page.value+1)

    }


     fun onChangeRecipeScrollPosition(position: Int) {
        setListScrollPosition(position = position)
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
                    is RecipeListEvent.RestoreStateEvent->{
                        restoreState()

                    }
                }

            }catch (e:Exception){
                Log.d(DEBUG_TAG, "onTriggerEvent: $event , cause ${e.cause} ")
            }
        }
    }

    private suspend fun restoreState() {
        loading.value = true
        val results : MutableList<Recipe> =  mutableListOf()

        for (p in 1..page.value){

            val result = repository.search(token= token, page = p, query = query.value)

            results.addAll(result)

            if(p==page.value){
                //done
                recipes.value = results
                loading.value = false
            }

        }



    }

    private suspend  fun nextPage() {
            //Prevent duplication due to recomposition.
            if ((recipeListScrolPosition + 1) >=  (page.value * PAGE_SIZE)) {
                loading.value = true
                incrementPage()
                Log.d(DEBUG_TAG, "nextPage triggered: page ${page.value}")
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
        setSelectedCategory(null)

    }
    private fun setListScrollPosition(position: Int){
        recipeListScrolPosition = position
        savedStateHandle[STATE_KEY_LIST_POSITION] = position
    }
    private fun setPage(page:Int){
        this.page.value = page
        savedStateHandle[STATE_KEY_PAGE] = page
    }

    private fun setSelectedCategory(category:FoodCategory?){
        selectedCategory.value = category
        savedStateHandle[STATE_KEY_SELECTED_CATEGORY] = category

    }

    private fun setQuery(query:String){
        this.query.value = query
        savedStateHandle[STATE_KEY_QUERY] = query
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