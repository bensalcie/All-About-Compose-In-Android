package bensalcie.samples.allaboutcompose_android.repository

import bensalcie.samples.allaboutcompose_android.domain.model.Recipe
import bensalcie.samples.allaboutcompose_android.network.model.RecipeDtoMapper
import bensalcie.samples.allaboutcompose_android.network.model.RecipeService

class RecipeRepositoryImpl (private val recipeService: RecipeService,private  val mapper: RecipeDtoMapper):RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        return mapper.toDomainModelList(recipeService.search(token=token, page = page,query = query).recipes)

    }

    override suspend fun get(token: String, id: Int): Recipe {
       return mapper.mapToDomainModel(recipeService.get(token = token,id= id))
    }
}