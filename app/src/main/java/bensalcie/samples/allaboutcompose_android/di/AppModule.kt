package bensalcie.samples.allaboutcompose_android.di

import android.app.Activity
import android.content.Context
import bensalcie.samples.allaboutcompose_android.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Global instance for di
//Make singleton Object
///[SingletonComponent] //Exist as long as application is alive
@Module
@InstallIn(SingletonComponent::class) //Define the component you want to install.
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app:Context):BaseApplication{

        return app as BaseApplication
    }
    @Singleton
    @Provides
    fun provideRandomString():String{
        return "Hey look a rando  string !!!"
    }


}