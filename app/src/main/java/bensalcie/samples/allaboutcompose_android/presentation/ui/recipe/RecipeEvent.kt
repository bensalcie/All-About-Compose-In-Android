package bensalcie.samples.allaboutcompose_android.presentation.ui.recipe

sealed class RecipeEvent {
    data class GetRecipeEvent(val id:Int):RecipeEvent()
}