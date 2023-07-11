package bensalcie.samples.allaboutcompose_android.di

import bensalcie.samples.allaboutcompose_android.network.model.RecipeDtoMapper
import bensalcie.samples.allaboutcompose_android.network.model.RecipeService
import bensalcie.samples.allaboutcompose_android.repository.RecipeRepository
import bensalcie.samples.allaboutcompose_android.repository.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  RepositoryModule {
    @Singleton
    @Provides
    fun provideRecipeRepository(recipeService: RecipeService, recipeDtoMapper: RecipeDtoMapper):RecipeRepository{
        return  RecipeRepositoryImpl(recipeService = recipeService, mapper = recipeDtoMapper)
    }

}