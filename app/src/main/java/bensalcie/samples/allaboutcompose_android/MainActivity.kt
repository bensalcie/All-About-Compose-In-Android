package bensalcie.samples.allaboutcompose_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
            Column(modifier = Modifier.fillMaxHeight()) {
                Image(painter = painterResource(id = R.drawable.humberger), contentDescription ="Hamburger photo" ,modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(), contentScale = ContentScale.Crop)
                    Spacer(modifier = Modifier.padding(15.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Happy mean Hamburger", style = TextStyle(fontSize = 24.sp))
                    Text(text = "$5.99", style = TextStyle(fontSize = 24.sp, color = Color.Green))


                }
                Spacer(modifier = Modifier.padding(15.dp))

                Text(text = "Happy mean Hamburger is now discounted for agiven amount of money and you should buy it.", style = TextStyle(fontSize = 20.sp), modifier = Modifier.padding(10.dp))

                Spacer(modifier = Modifier.padding(15.dp))
                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(14.dp).align(Alignment.CenterHorizontally)) {
                    Text(text = "Order Now")
                    
                }



            }
        }



        //Hamburger task.



        ///Basic Row Arrangment.
//        setContent {
//            Column {
//
//                Column(modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//                    .border(border = BorderStroke(width = 1.dp, color = Color.Black)), verticalArrangement = Arrangement.SpaceAround) {
//                    Text(text = "ITEM 1", modifier = Modifier.align(Alignment.CenterHorizontally))
//                    Text(text = "ITEM 1", modifier = Modifier.align(Alignment.CenterHorizontally))
//
//
//                }
//                Spacer(modifier = Modifier.padding(20.dp))
//
//                Row(modifier = Modifier
//                    .width(200.dp)
//                    .height(200.dp)
//                    .border(border = BorderStroke(width = 1.dp, color = Color.Black)), horizontalArrangement = Arrangement.Center) {
//
//                    Text(text = "ITEM 2", modifier = Modifier.align(Alignment.CenterVertically))
//                }
//            }
//
//
//        }
//
        /// Basic Collumn Arrangement.
      //setContent {


//          Column (  modifier = Modifier.fillMaxHeight()
//              .fillMaxWidth()
//              .background(Color(0xFFF2F2F2))
//              .verticalScroll(
//                  rememberScrollState()
//              )){
//              Image(painter = painterResource(id = R.drawable.humberger),
//
//
//                  contentDescription = "Humberger Photo", modifier = Modifier.height(300.dp).align(alignment = Alignment.CenterHorizontally), contentScale = ContentScale.Crop)
//
//              Column(modifier = Modifier.padding(16.dp)) {
//                  Text(text = "Humberger Chilli", style = TextStyle(fontSize = 26.sp))
//                  Spacer(modifier = Modifier.padding(10.dp))
//                  Text(text = "800 Calories", style = TextStyle(fontSize = 17.sp))
//                  Spacer(modifier = Modifier.padding(10.dp))
//                  Text(text = "$5.99", style = TextStyle(fontSize = 17.sp, color = Color(0xff85bb65)))
//
//
//
//
//              }
//          }
  //    }
    }


}
