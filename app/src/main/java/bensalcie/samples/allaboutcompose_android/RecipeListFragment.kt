package bensalcie.samples.allaboutcompose_android

import android.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.text.Layout.Alignment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContentProviderCompat.requireContext
import com.codingwithmitch.foodrecipes.util.HorizontalDottedProgress

class RecipeListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //Views plus composables.
        val view = inflater?.inflate(R.layout.fragment_recipe_list,container,false)
        view?.findViewById<ComposeView>(R.id.compose_view)?.apply {
            setContent {
                Column(
                    modifier = Modifier
                        .border(
                            border = BorderStroke(
                                1.dp,
                                color = androidx.compose.ui.graphics.Color.Red
                            )
                        )
                        .padding(16.dp)
                ) {
                    Text(text = "THIS IS A COMPOSABLE INSIDE THE FRAGMENT")
                    Spacer(modifier = Modifier.padding(10.dp))
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(text = "NEAT")
                    Spacer(modifier = Modifier.padding(10.dp))
                    val customView = HorizontalDottedProgress(context = context)
                    AndroidView(factory = {customView})
                    Spacer(modifier = Modifier.padding(10.dp))




                }

            }
        }
        return view


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