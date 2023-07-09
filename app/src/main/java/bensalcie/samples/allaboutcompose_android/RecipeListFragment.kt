package bensalcie.samples.allaboutcompose_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.findNavController

class RecipeListFragment : androidx.fragment.app.Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return  ComposeView(requireContext()).apply {
            setContent {

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Recipe List",
                    
                        style = TextStyle(fontSize = 21.sp)
                        )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(onClick = {

                        findNavController().navigate(R.id.recipeFragment)
                    }) {
                        Text(text = "TO RECIPE FRAGMENT")
                        
                    }
                    
                    

                }

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