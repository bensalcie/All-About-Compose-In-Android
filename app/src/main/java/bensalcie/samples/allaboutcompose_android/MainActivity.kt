package bensalcie.samples.allaboutcompose_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bensalcie.samples.allaboutcompose_android.ui.theme.AllAboutComposeAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      setContent {
          Column(modifier = Modifier.fillMaxWidth()) {

              Text(text = "Hello Compose")
              Spacer(modifier = Modifier.padding(top = 23.dp))
              Button(onClick = { }) {
                  Text(text = "Click Me")

              }
          }
      }
    }
}
