package bensalcie.samples.allaboutcompose_android.presentation.components

import ShimmerCardLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bensalcie.samples.allaboutcompose_android.domain.model.Recipe
import bensalcie.samples.allaboutcompose_android.presentation.ui.recipe.recipelist.PAGE_SIZE
import bensalcie.samples.allaboutcompose_android.presentation.ui.recipe.recipelist.RecipeListEvent

@Composable
fun RecipeList(
    isLoading: Boolean,
    recipes: List<Recipe>,
    onChangeRecipeScrollPosition: (Int) -> Unit,
    onTriggerEvent: (RecipeListEvent) -> Unit,
    page: Int

) {


    Box(
        modifier = Modifier
            .padding(top = 130.dp)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        if (isLoading && recipes.isEmpty()) {
            LazyColumn {

                /*
              Lay down the Shimmer Animated item 5 time
              [repeat] is like a loop which executes the body
              according to the number specified
            */
                repeat(5) {
                    item {
                        ShimmerCardLayout(cardHeight = 250.dp)
                    }
                }
            }
        } else {


            LazyColumn {


                itemsIndexed(items = recipes) { index, item ->
                    onChangeRecipeScrollPosition(index)
                    if ((index + 1) >= page * PAGE_SIZE && !isLoading) {
                        onTriggerEvent(RecipeListEvent.NextPageEvent)
                    }
                    RecipeCard(recipe = item, onclick = {})
                }

            }
        }

    }
}