package bensalcie.samples.allaboutcompose_android.presentation.ui.recipe

import ShimmerCardLayout
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import bensalcie.samples.allaboutcompose_android.presentation.BaseApplication
import bensalcie.samples.allaboutcompose_android.presentation.components.CircularIndeterminateProgressBar
import bensalcie.samples.allaboutcompose_android.presentation.components.DefaultSnackBar
import bensalcie.samples.allaboutcompose_android.presentation.components.RecipeView
import bensalcie.samples.allaboutcompose_android.presentation.ui.recipe.recipelist.RecipeListViewModel
import bensalcie.samples.allaboutcompose_android.ui.theme.AllAboutComposeAndroidTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeFragment : Fragment() {
    //    private val viewModel by activityViewModels<RecipeListViewModel>()
    private val viewModel by viewModels<RecipeViewModel>()

    @Inject
    lateinit var application: BaseApplication





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("recipeId")?.let { selectedrecipeId ->
            viewModel.onTriggerEvent(RecipeEvent.GetRecipeEvent(selectedrecipeId))

        }
        print("Compose Fragment 2: $viewModel")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(context = requireContext()).apply {
            setContent {
                val isLoading = viewModel.isLoading.value
                val recipe = viewModel.recipe.value

                val snackbarHostState = remember { SnackbarHostState() }

                AllAboutComposeAndroidTheme(darkTheme = application.isDark.value) {
                   Box(modifier = Modifier.fillMaxSize()){
                       if(isLoading && recipe==null){
                           Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                               ShimmerCardLayout(cardHeight = 260.dp)
                               ShimmerCardLayout(cardHeight = 50.dp)

                               ShimmerCardLayout(cardHeight = 40.dp)
                               ShimmerCardLayout(cardHeight = 40.dp)
                               ShimmerCardLayout(cardHeight = 40.dp)
                               ShimmerCardLayout(cardHeight = 40.dp)

                           }


                       }else{
                           recipe?.let {
                                   RecipeView(recipe = recipe)

                           }
                       }
                       CircularIndeterminateProgressBar(
                           isDisplayed = isLoading,
                           verticalBias = 0.3f
                       )
                       DefaultSnackBar(snackbarHostState = snackbarHostState, modifier = Modifier.align(
                           Alignment.Center
                       ), onDismiss = {snackbarHostState.currentSnackbarData?.dismiss()} )
                   }


                }
            }
        }
    }
}