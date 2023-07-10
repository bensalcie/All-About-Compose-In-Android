package bensalcie.samples.allaboutcompose_android.repository

import bensalcie.samples.allaboutcompose_android.domain.model.Recipe

interface RecipeRepository {
    suspend fun search(token:String, page:Int, query:String):List<Recipe>
    suspend fun get(token:String,id:Int):Recipe
}