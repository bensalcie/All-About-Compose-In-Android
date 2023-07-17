package bensalcie.samples.allaboutcompose_android.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {
    //Global variables
    //Should be saved in datastore or cashe
    val isDark = mutableStateOf(false)
    fun toggleLightTheme(){
        isDark.value = !isDark.value
    }
}