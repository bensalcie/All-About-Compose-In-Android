package bensalcie.samples.allaboutcompose_android.presentation.components

import android.text.Layout.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import bensalcie.samples.allaboutcompose_android.R
import bensalcie.samples.allaboutcompose_android.domain.model.Recipe
import bensalcie.samples.allaboutcompose_android.utils.DEFAULT_RECIPE_IMAGE
import bensalcie.samples.allaboutcompose_android.utils.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val IMAGE_HEIGHT = 260
@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun RecipeView(recipe: Recipe) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        recipe.featured_image?.let {url->
            val image = loadPicture(url = url, defaultImage = DEFAULT_RECIPE_IMAGE).value
            image?.let {img->
                Image(bitmap =img.asImageBitmap() , contentDescription = recipe.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IMAGE_HEIGHT.dp),
                    contentScale = ContentScale.Crop
                )

            }

        }
        recipe.title?.let {



            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),) {
                Text(text = recipe.title, modifier = Modifier
                    .fillMaxWidth(0.85f)
                    , style = MaterialTheme.typography.titleLarge,textAlign = TextAlign.Start)
                val rank = recipe.rating.toString()
                Text(text = rank, modifier = Modifier
                    .fillMaxWidth(), textAlign = TextAlign.Center, style = MaterialTheme.typography.titleSmall)





            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {


            recipe.publisher?.let {publisher->
                
                
                val updated = recipe.date_updated
                
                val byWho = if (updated!=null){
                    "Updated $updated by $publisher"
                }else{
                    "By $publisher"
                }
                Text(text = byWho, modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp), style = MaterialTheme.typography.titleSmall)

                recipe.ingredients?.let {

                    for (ingredient in recipe.ingredients){
                        Text(text = ingredient, modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp), style = MaterialTheme.typography.bodyMedium)

                    }
                }
                
                
                
                
            }

        }
    }



}