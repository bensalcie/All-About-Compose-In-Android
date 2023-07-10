package bensalcie.samples.allaboutcompose_android.network.model.responses

import bensalcie.samples.allaboutcompose_android.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(

    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>,
)