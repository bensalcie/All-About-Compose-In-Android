package bensalcie.samples.allaboutcompose_android.presentation.ui.recipe.recipelist

sealed class RecipeListEvent {
    object NewSearchEvent : RecipeListEvent()
    object NextPageEvent : RecipeListEvent()

    // Restore after process death.
    object RestoreStateEvent : RecipeListEvent()

}