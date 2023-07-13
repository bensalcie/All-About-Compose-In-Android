package bensalcie.samples.allaboutcompose_android.presentation.components

import android.graphics.Paint.Style
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean) {
    if (isDisplayed) {
        //Using with screen orientation.
//        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
//            val constraints = if (minWidth < 600.dp) { //We are in portrait
//                myDecoupledConstraints(0.3f)
//            } else {
//                myDecoupledConstraints(0.7f)
//            }
//            ConstraintLayout(modifier = Modifier.fillMaxSize(), constraintSet = constraints) {
//
//                CircularProgressIndicator(
//                    color = MaterialTheme.colorScheme.primary,
//                    modifier = Modifier.layoutId("progressBar"))
//
//                Text(text = "Loading...",
//
//
//                    modifier = Modifier.layoutId("text"), style = TextStyle(color = Color.Black, fontSize = 15.sp)
//                )
//
//            }
//        }



        //Previous.


        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (progressBar, text) = createRefs()
            val topGuideLine = createGuidelineFromTop(0.3F)// Creates a guideline from top and the children follow.
//            val bottomGuideLine =
//                createGuidelineFromBottom(0.3F)// Creates a guideline from top and the children follow.

            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.constrainAs(progressBar) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                })

            Text(text = "Loading...",


                modifier = Modifier.constrainAs(text) {
                    top.linkTo(progressBar.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }, style = TextStyle(color = Color.Black, fontSize = 15.sp)
            )

        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(20.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            CircularProgressIndicator(color=MaterialTheme.colorScheme.primary)
//
//        }
    }

}
//Decoupled code.
//private fun myDecoupledConstraints(verticalBias: Float): ConstraintSet {
//    return ConstraintSet {
//        val guideline = createGuidelineFromTop(verticalBias)
//        val progressBar = createRefFor("progressBar")
//        val text = createRefFor("text")
//        constrain(progressBar) {
//            top.linkTo(guideline)
//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
//        }
//        constrain(text) {
//            top.linkTo(progressBar.bottom)
//            end.linkTo(parent.end)
//            start.linkTo(parent.start)
//        }
//    }
//}