package bensalcie.samples.allaboutcompose_android.network.model

import bensalcie.samples.allaboutcompose_android.domain.model.Recipe
import bensalcie.samples.allaboutcompose_android.domain.model.util.EntityMapper

class RecipeDtoMapper:EntityMapper<RecipeDto,Recipe> {
    override fun mapToDomainModel(model: RecipeDto): Recipe {
        return  Recipe(
            pk = model.pk,
            title = model.title,
            date_added = model.dateAdded,
            date_updated = model.dateUpdated,
            description = model.description,
            featured_image = model.featuredImage,
            ingredients = model.ingredients,
            long_date_added = model.longDateAdded,
            cooking_instructions = model.cookingInstructions.toString(),
            publisher = model.publisher,
            rating = model.rating,
            long_date_updated = model.longDateUpdated,
            source_url = model.sourceUrl
        )
    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeDto {
        return RecipeDto(
            rating = domainModel.rating,
            publisher = domainModel.publisher,
            title = domainModel.title,
            pk = domainModel.pk,
            ingredients = domainModel.ingredients,
            description = domainModel.description,
            cookingInstructions = domainModel.cooking_instructions,
            dateAdded = domainModel.date_added,
             dateUpdated = domainModel.date_updated,
             featuredImage = domainModel.featured_image,
            longDateAdded = domainModel.long_date_added,
            longDateUpdated = domainModel.long_date_updated,
            sourceUrl = domainModel.source_url
        )
    }

    override fun toDomainModelList(initial: List<RecipeDto>): List<Recipe> {
        return  initial.map { mapToDomainModel(it) }
    }

    override fun fromDomainModelList(initial: List<Recipe>): List<RecipeDto> {
       return  initial.map { mapFromDomainModel(it) }
    }


}