package bensalcie.samples.allaboutcompose_android.presentation.ui.recipe.recipelist

import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import bensalcie.samples.allaboutcompose_android.R
import bensalcie.samples.allaboutcompose_android.presentation.components.RecipeCard
import bensalcie.samples.allaboutcompose_android.utils.DEBUG_TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : androidx.fragment.app.Fragment() {
    private val viewModel  by viewModels<RecipeListViewModel>()
    //Get instance in the fragment only.

//    private val viewModel  by activityViewModels <RecipeListViewModel>() //Get instance in the fragment only.

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d("ViewModel", "random string ${viewModel.getRandomString()}")
//        Log.d("ViewModel", "token ${viewModel.getToken()}")
//        Log.d("ViewModel", "repository ${viewModel.getRepository()}")
//    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {




        return  ComposeView(requireContext()).apply {
            setContent {
                                   val recipes = viewModel.recipes.value

                LazyColumn {


                    itemsIndexed(items = recipes){
                            _, item ->
                        RecipeCard(recipe = item, onclick = {})
                    }
                }




//                Column(modifier = Modifier.padding(16.dp)) {
//
//                    val recipes = viewModel.recipes.value
//
//                    for(recipe in recipes){
//                        Log.d(DEBUG_TAG, "onCreateView: ")
//                        RecipeCard(recipe = recipe) {
//
//
//                        }
//                        Spacer(modifier = Modifier.padding(10.dp))
//                    }
//                    Text(text = "Recipe List",
//
//                        style = TextStyle(fontSize = 21.sp)
//                        )
//                    Spacer(modifier = Modifier.padding(10.dp))
//
//                    Button(onClick = {
//
//                        findNavController().navigate(R.id.recipeFragment)
//                    }) {
//                        Text(text = "TO RECIPE FRAGMENT")
//
//                    }
//
//
//
//                }

            }
        }


        //Views plus composables testing
//        val view = inflater?.inflate(R.layout.fragment_recipe_list,container,false)
//        view?.findViewById<ComposeView>(R.id.compose_view)?.apply {
//            setContent {
//                Column(
//                    modifier = Modifier
//                        .border(
//                            border = BorderStroke(
//                                1.dp,
//                                color = androidx.compose.ui.graphics.Color.Green
//                            )
//                        )
//                        .padding(16.dp)
//                ) {
//                    Text(text = "THIS IS A COMPOSABLE INSIDE THE FRAGMENT")
//                    Spacer(modifier = Modifier.padding(10.dp))
//                    CircularProgressIndicator()
//                    Spacer(modifier = Modifier.padding(10.dp))
//                    Text(text = "NEAT")
//                    Spacer(modifier = Modifier.padding(10.dp))
//                    val customView = HorizontalDottedProgress(context = context)
//                    AndroidView(factory = {customView})
//                    Spacer(modifier = Modifier.padding(10.dp))
//
//
//
//
//                }
//
//            }
//        }
//        return view


        //Short Hand
//        return  ComposeView(context = context).apply {
//            setContent {
//                Text(text = "Recipe List Fragment")
//            }
//        }

        //Returning the vieWs way
        //return inflater?.inflate(R.layout.fragment_recipe_list, container, false)
//        val view = ComposeView(context)
//        view.apply {
//            setContent {
//                Column(modifier = Modifier.fillMaxHeight(), ) {
//                    Text("Showing from Composable",modifier =  Modifier.align(CenterHorizontally))
//                }
//
//            }
//        }
 }
}