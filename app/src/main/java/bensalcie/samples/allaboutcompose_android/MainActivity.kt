package bensalcie.samples.allaboutcompose_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        //Nothing required here as Navigation components will handle that.







        // Testing views system
//        setContentView(R.layout.main_activity)
//        fragmentManager.beginTransaction()
//            .replace(R.id.main_container,RecipeListFragment())
//            .commit()
//





        //Hamburger task.
        //Lessson 4
//        setContent {
//            Column(modifier = Modifier.fillMaxHeight()) {
//                Image(painter = painterResource(id = R.drawable.humberger), contentDescription ="Hamburger photo" ,modifier = Modifier
//                    .height(300.dp)
//                    .fillMaxWidth(), contentScale = ContentScale.Crop)
//                Spacer(modifier = Modifier.padding(15.dp))
//                Row(modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
//                    Text(text = "Happy mean Hamburger", style = TextStyle(fontSize = 24.sp))
//                    Text(text = "$5.99", style = TextStyle(fontSize = 24.sp, color = Color.Green))
//
//
//                }
//                Spacer(modifier = Modifier.padding(15.dp))
//
//                Text(text = "Happy mean Hamburger is now discounted for agiven amount of money and you should buy it.", style = TextStyle(fontSize = 20.sp), modifier = Modifier.padding(10.dp))
//
//                Spacer(modifier = Modifier.padding(15.dp))
//                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(14.dp).align(Alignment.CenterHorizontally)) {
//                    Text(text = "Order Now")
//
//                }
//
//
//
//            }
//        }


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
