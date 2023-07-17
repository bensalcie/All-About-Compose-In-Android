package bensalcie.samples.allaboutcompose_android.presentation.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerItem(brush: Brush,cardHeight:Dp){

    Column() {
        Surface(shape = MaterialTheme.shapes.small) {
            Spacer(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp)
                .fillMaxWidth()
                .height(cardHeight)
                .background(brush))

        }
        Surface(shape = MaterialTheme.shapes.small) {
            Spacer(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp)
                .fillMaxWidth()
                .height(cardHeight/10)
                .background(brush))

        }
    }
}