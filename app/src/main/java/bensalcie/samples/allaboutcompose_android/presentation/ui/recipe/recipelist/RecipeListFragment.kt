package bensalcie.samples.allaboutcompose_android.presentation.ui.recipe.recipelist

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment.Companion.CenterVertically

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import bensalcie.samples.allaboutcompose_android.R
import bensalcie.samples.allaboutcompose_android.presentation.components.FoodCategoryChip
import bensalcie.samples.allaboutcompose_android.presentation.components.RecipeCard
import bensalcie.samples.allaboutcompose_android.utils.DEBUG_TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipeListFragment : androidx.fragment.app.Fragment() {
    private val viewModel by viewModels<RecipeListViewModel>()
    //Get instance in the fragment only.

//    private val viewModel  by activityViewModels <RecipeListViewModel>() //Get instance in the fragment only.

    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d("ViewModel", "random string ${viewModel.getRandomString()}")
//        Log.d("ViewModel", "token ${viewModel.getToken()}")
//        Log.d("ViewModel", "repository ${viewModel.getRepository()}")
//    }
    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return ComposeView(requireContext()).apply {
            setContent {
                val keyboardController = LocalSoftwareKeyboardController.current

                val recipes = viewModel.recipes.value

                val selectedCategory = viewModel.selectedCategory.value
                val query = viewModel.query.value //Maintain configuration changes.
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Surface(
                        shadowElevation = 8.dp,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White

                    ) {

                        Column {


                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                TextField(
                                    value = query,
                                    modifier = Modifier
                                        .fillMaxWidth(

                                        )
                                        .padding(8.dp),
                                    label = { Text(text = "Search") },

                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Search
                                    ),


                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = "Search"
                                        )
                                    },


                                    keyboardActions = KeyboardActions(onSearch =

                                    {
                                        viewModel.newSearch()

                                        keyboardController?.hide()


                                    }),

                                    onValueChange = { newValue -> viewModel.onQueryChange(newValue) })


                            }
                            val scrollState = rememberLazyListState()

                            val scope = rememberCoroutineScope()

                            scope.launch { scrollState.animateScrollToItem(viewModel.categoryScrollPosition.toInt()) }


                            //Scrollable Row
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()

                                    .padding(start = 8.dp, bottom = 8.dp)
                            , state = scrollState
                            ) {
                                itemsIndexed(items = getAllFoodCategories()) {

                                        index, item ->
                                    FoodCategoryChip(
                                        category = item.value,
                                        isSelected = selectedCategory == item,
                                        onExecuteSearch = viewModel::newSearch,
                                        onSelectedCategoryChanged = {
                                            viewModel.onSelectedCategoryChanged(it)
                                            viewModel.onChangeScrollPosition(index.toFloat())
                                        })


//                                  Text(
//                                      text = item.name,
//                                      style = MaterialTheme.typography.bodyLarge,
//                                      color = MaterialTheme.colorScheme.onBackground,
//                                      modifier = Modifier.padding(8.dp)
//                                  )

                                }

                            }

                        }


                    }


                    if (recipes.isNotEmpty()) {


                        LazyColumn {


                            itemsIndexed(items = recipes) { _, item ->
                                RecipeCard(recipe = item, onclick = {})
                            }
                        }
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