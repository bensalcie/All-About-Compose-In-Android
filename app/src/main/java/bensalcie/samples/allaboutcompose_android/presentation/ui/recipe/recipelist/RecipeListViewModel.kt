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
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

///Use ViewModelInject in the constructor to inject components to the viewmodel.
@HiltViewModel
class RecipeListViewModel @Inject constructor(
                                              private val repository: RecipeRepository,
                                              @Named("auth_token") private val token:String
                                              ):ViewModel() {

//Old model
    //  private  val _recipes :MutableLiveData<List<Recipe>> = MutableLiveData()
//     val recipes : LiveData<List<Recipe>> get() = _recipes

    val recipes : MutableState<List<Recipe>> = mutableStateOf(listOf())

    val selectedCategory :MutableState<FoodCategory?> = mutableStateOf(null)

    var categoryScrollPosition :Int = 0
    var loading = mutableStateOf(false)

    var query = mutableStateOf("")
init {
  newSearch()
}
     fun newSearch(){
         loading.value = true
         resetSearchState()
        viewModelScope.launch {
            delay(2000)

            val result =  repository.search(token = token, page = 1, query = query.value)
            recipes.value = result
            loading.value= false

        }
    }

    fun onQueryChange(query:String){
        this.query.value= query
    }

    fun onSelectedCategoryChanged(category:String){
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChange(category)
    }
fun onChangeScrollPosition(position:Int){
    this.categoryScrollPosition = position
}

    private fun resetSearchState(){
        recipes.value = listOf()
        if (selectedCategory.value?.value!=query.value)
            clearSelectedCategory()

    }
    private fun clearSelectedCategory(){
        this.selectedCategory.value =null

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