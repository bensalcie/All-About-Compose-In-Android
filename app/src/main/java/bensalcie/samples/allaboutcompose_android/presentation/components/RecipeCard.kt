package bensalcie.samples.allaboutcompose_android.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import bensalcie.samples.allaboutcompose_android.R
import bensalcie.samples.allaboutcompose_android.domain.model.Recipe
import bensalcie.samples.allaboutcompose_android.utils.DEFAULT_RECIPE_IMAGE
import bensalcie.samples.allaboutcompose_android.utils.loadPicture
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun RecipeCard (recipe: Recipe,onclick:()->Unit){
   Card(
       shape = MaterialTheme.shapes.small,
       modifier = Modifier
           .padding(top = 10.dp, bottom = 10.dp)
           .fillMaxWidth()
           .clickable { onclick() }) {
       Column(modifier = Modifier.fillMaxWidth()) {
         recipe.featured_image.let {url->
                 val image = url?.let { loadPicture(url = it, defaultImage = DEFAULT_RECIPE_IMAGE).value }
                 image?.let { img ->
                     Image(
                         bitmap = img.asImageBitmap(),
                         modifier = Modifier
                             .fillMaxWidth()
                             .height(225.dp),
                         contentScale = ContentScale.Crop,
                         contentDescription = recipe.title
                     )

             }


         }
           Row(modifier = Modifier
               .fillMaxWidth()
               .padding(top = 10.dp, bottom = 10.dp, start = 8.dp)) {
               recipe.title?.let { Text(text = it, style = MaterialTheme.typography.titleMedium, modifier = Modifier
                   .fillMaxWidth(0.85f)
                   .wrapContentWidth(
                       Alignment.Start
                   )) }
               Text(text = recipe.rating.toString(), style = MaterialTheme.typography.bodySmall, modifier = Modifier
                   .fillMaxWidth()
                   .wrapContentWidth(
                       Alignment.Start
                   )
                   .align(CenterVertically))


           }

       }

   } 
}