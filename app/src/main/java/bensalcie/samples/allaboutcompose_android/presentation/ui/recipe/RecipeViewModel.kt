package bensalcie.samples.allaboutcompose_android.presentation.ui.recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bensalcie.samples.allaboutcompose_android.domain.model.Recipe
import bensalcie.samples.allaboutcompose_android.repository.RecipeRepository
import bensalcie.samples.allaboutcompose_android.utils.DEBUG_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named


 const val STATE_KEY_RECIPE = "state.ke.recipe"

@HiltViewModel
class RecipeViewModel @Inject
constructor(

    private val recipeRepository: RecipeRepository,
    @Named("auth_token") private val token: String,
    private val savedStateHandle: SavedStateHandle
):ViewModel() {
     val recipe: MutableState<Recipe?> = mutableStateOf(null)

     val isLoading = mutableStateOf(false)

    init {
        //Restore if process dies.
        savedStateHandle.get<Int>(STATE_KEY_RECIPE)?.let { recipe-> onTriggerEvent(RecipeEvent.GetRecipeEvent(recipe)) }
    }

     fun onTriggerEvent(event: RecipeEvent.GetRecipeEvent) {
        viewModelScope.launch {
            try {
                when(event){
                    is RecipeEvent.GetRecipeEvent->{
                        if (recipe.value == null) {
                            getRecipe(event.id)
                        }

                    }
                }

            }catch (e:Exception){
                Log.d(DEBUG_TAG, "onTriggerEvent:${e}, ${e.cause}")
            }
        }

    }

    private suspend fun getRecipe(id: Int) {
        isLoading.value = true
        val recipe = recipeRepository.get(token =  token,id= id)
        this.recipe.value = recipe
        savedStateHandle [STATE_KEY_RECIPE] = recipe.pk
        isLoading.value = false



    }

}