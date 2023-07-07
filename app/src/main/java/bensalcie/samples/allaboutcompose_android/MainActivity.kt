package bensalcie.samples.allaboutcompose_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bensalcie.samples.allaboutcompose_android.ui.theme.AllAboutComposeAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      setContent {


          Column (  modifier = Modifier.fillMaxHeight()
              .fillMaxWidth()
              .background(Color(0xFFF2F2F2))
              .verticalScroll(
                  rememberScrollState()
              )){
              Image(painter = painterResource(id = R.drawable.humberger),


                  contentDescription = "Humberger Photo", modifier = Modifier.height(300.dp).align(alignment = Alignment.CenterHorizontally), contentScale = ContentScale.Crop)

              Column(modifier = Modifier.padding(16.dp)) {
                  Text(text = "Humberger Chilli", style = TextStyle(fontSize = 26.sp))
                  Spacer(modifier = Modifier.padding(10.dp))
                  Text(text = "800 Calories", style = TextStyle(fontSize = 17.sp))
                  Spacer(modifier = Modifier.padding(10.dp))
                  Text(text = "$5.99", style = TextStyle(fontSize = 17.sp, color = Color(0xff85bb65)))




              }
          }
      }
    }


}
