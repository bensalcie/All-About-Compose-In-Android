package bensalcie.samples.allaboutcompose_android.presentation.ui.recipe.recipelist

import ShimmerCardLayout
import android.annotation.SuppressLint
import android.graphics.Paint.Style
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import bensalcie.samples.allaboutcompose_android.presentation.BaseApplication
import bensalcie.samples.allaboutcompose_android.presentation.components.CircularIndeterminateProgressBar
import bensalcie.samples.allaboutcompose_android.presentation.components.RecipeCard
import bensalcie.samples.allaboutcompose_android.presentation.components.SearchAppBar
import bensalcie.samples.allaboutcompose_android.ui.theme.AllAboutComposeAndroidTheme
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : androidx.fragment.app.Fragment() {
    private val viewModel by viewModels<RecipeListViewModel>()

    @Inject
    lateinit var application: BaseApplication

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return ComposeView(requireContext()).apply {


            setContent {


                //Example showing how to display snackbar

//                val isShowing = remember {
//                    mutableStateOf(true)
//                }
//
//                val snackbarHostState = remember {
//                    SnackbarHostState()
//                }
//
//                Column {
//                    Button(onClick = {
//                       lifecycleScope.launch {
//                           snackbarHostState.showSnackbar(
//                               message = "Hey this is a snackbar",
//                               duration = SnackbarDuration.Short,
//                               actionLabel = "Dismiss",
//                           )
//                       }
//                    }) {
//                        Text(text = "Show Snackbar")
//                    }
//                    DecoupledSnackbarDemo(snackbarHostState = snackbarHostState)
//                    SnackbarDemo(
//                        isShowing = isShowing.value,
//                        onHideSnackbar = { isShowing.value = false })

                //}


                AllAboutComposeAndroidTheme(darkTheme = application.isDark.value) {


                    val recipes = viewModel.recipes.value

                    val selectedCategory = viewModel.selectedCategory.value
                    val query = viewModel.query.value //Maintain configuration changes.
                    val isLoading = viewModel.loading.value
                    val snackbarHostState = remember { SnackbarHostState() }
                    val page = viewModel.page.value


                    Scaffold(
                        snackbarHost = {
                            // reuse default SnackbarHost to have default animation and timing handling
                            SnackbarHost(snackbarHostState) { data ->
                                // custom snackbar with the custom action button color and border
                                val isError =
                                    (data.visuals as? SnackbarVisualsWithError)?.isError ?: false
                                val buttonColor = if (isError) {
                                    ButtonDefaults.textButtonColors(
                                        containerColor = MaterialTheme.colorScheme.errorContainer,
                                        contentColor = MaterialTheme.colorScheme.error
                                    )
                                } else {
                                    ButtonDefaults.textButtonColors(
                                        contentColor = MaterialTheme.colorScheme.inversePrimary
                                    )
                                }

                                Snackbar(
                                    modifier = Modifier
                                        .border(2.dp, MaterialTheme.colorScheme.secondary)
                                        .padding(12.dp),
                                    action = {
                                        TextButton(
                                            onClick = { if (isError) data.dismiss() else data.performAction() },
                                            colors = buttonColor
                                        ) { Text(data.visuals.actionLabel ?: "") }
                                    }
                                ) {
                                    Text(data.visuals.message)
                                }
                            }
                        },

                        topBar = {
                            SearchAppBar(
                                query = query,
                                onQueryChanged = viewModel::onQueryChange,
                                onExecuteSearch = {
                                    viewModel.onTriggerEvent(RecipeListEvent.NewSearchEvent)
                                },
                                selectedCategory = selectedCategory,
                                scrollPosition = viewModel.categoryScrollPosition,
                                onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                                onChangeScrollPosition = viewModel::onChangeScrollPosition,
                                ontoggleTheme = {
                                    application.toggleLightTheme()

                                    val mode = when(application.isDark.value){
                                        true->"Dark"
                                        else->"Light"
                                    }
                                    lifecycleScope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = "Hurrey, you changed to $mode theme",
                                            actionLabel = "Dismiss",
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
                            )
                        },

//                        bottomBar = {
//                            MyBottomBar()
//
//
//                        },

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


                                    itemsIndexed(items = recipes) {
                                            index, item ->
                                        viewModel.onChangeRecipeScrollPosition(index)
                                        if((index+1)>=page* PAGE_SIZE &&!isLoading){
                                            viewModel.onTriggerEvent(RecipeListEvent.NextPageEvent)
                                        }
                                        RecipeCard(recipe = item, onclick = {})
                                    }

                                }
                            }

                        }
                        CircularIndeterminateProgressBar(
                            isDisplayed = isLoading,
                            verticalBias = 0.3f
                        )


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
//Bottom bar example
//@Composable
//fun MyBottomBar() {
//    BottomAppBar(tonalElevation = 14.dp) {
//        NavigationBarItem(selected = true, onClick = { }, icon = {
//            Icon(
//                imageVector = Icons.Default.Home,
//                contentDescription = "Home"
//            )
//        })
//        NavigationBarItem(selected = false, onClick = { }, icon = {
//            Icon(
//                imageVector = Icons.Default.PlayArrow,
//                contentDescription = "Play"
//            )
//        })
//        NavigationBarItem(selected = false, onClick = { }, icon = {
//            Icon(
//                imageVector = Icons.Default.Person,
//                contentDescription = "Profile"
//            )
//        })
//
//
//    }
//}


@Composable
fun DecoupledSnackbarDemo(snackbarHostState: SnackbarHostState) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val snacbar = createRef()
        SnackbarHost(modifier = Modifier.constrainAs(snacbar) {
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
        },

            hostState = snackbarHostState,
            snackbar = {
                Snackbar(action = {
                    TextButton(onClick = { snackbarHostState.currentSnackbarData?.dismiss() }) {
                        Text(text = "Dismiss", style = TextStyle(color = Color.White))
                    }
                }) {
                    Text(
                        text = "Click me",
                        style = MaterialTheme.typography.headlineMedium
                    )

                }
            }

        )

    }
}

class SnackbarVisualsWithError(
    override val message: String,
    val isError: Boolean
) : SnackbarVisuals {
    override val actionLabel: String
        get() = if (isError) "Error" else "OK"
    override val withDismissAction: Boolean
        get() = false
    override val duration: SnackbarDuration
        get() = SnackbarDuration.Indefinite
}

@Composable
fun SnackbarDemo(onHideSnackbar: () -> Unit, isShowing: Boolean) {

    if (isShowing) {
        ConstraintLayout() {
            val snacbar = createRef()
            Snackbar(modifier = Modifier.constrainAs(snacbar) {
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }, action = {
                Text(
                    text = "Click me",
                    modifier = Modifier.clickable { onHideSnackbar() },
                    style = MaterialTheme.typography.headlineMedium
                )
            }) {

                Text(text = "Hey look a snackbar")

            }

        }
    }
}