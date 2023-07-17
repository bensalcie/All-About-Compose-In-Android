package bensalcie.samples.allaboutcompose_android.presentation.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import bensalcie.samples.allaboutcompose_android.presentation.BaseApplication
import bensalcie.samples.allaboutcompose_android.presentation.ui.recipe.recipelist.RecipeListViewModel
import bensalcie.samples.allaboutcompose_android.ui.theme.AllAboutComposeAndroidTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeFragment:Fragment() {
//    private val viewModel by activityViewModels<RecipeListViewModel>()
    private val viewModel by viewModels<RecipeListViewModel>()
    @Inject
    lateinit var application: BaseApplication


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        print("Compose Fragment 2: $viewModel")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return  ComposeView(context = requireContext()).apply {
            setContent {

                AllAboutComposeAndroidTheme(darkTheme = application.isDark.value) {

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "RECIPE FRAGMENT",

                            style = TextStyle(fontSize = 21.sp)
                        )
                        Spacer(modifier = Modifier.padding(10.dp))




                    }

                } }
                }
    }
}