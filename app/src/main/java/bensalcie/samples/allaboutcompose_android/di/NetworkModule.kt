package bensalcie.samples.allaboutcompose_android.di

import bensalcie.samples.allaboutcompose_android.network.model.RecipeDtoMapper
import bensalcie.samples.allaboutcompose_android.network.model.RecipeService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun  provideRecipeMapper():RecipeDtoMapper{
        return  RecipeDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRecipeService():RecipeService{
        return  Retrofit.Builder()
            .baseUrl("https://food2fork.ca/api/recipe/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RecipeService::class.java)
    }
    @Singleton
    @Provides
    @Named("auth_token")
    fun provideAuthToken():String{
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }


}