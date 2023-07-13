package bensalcie.samples.allaboutcompose_android.presentation.ui.recipe.recipelist


enum class FoodCategory(val value: String) {
    CHICKEN("Chicken"),
    BEEF("Beef"),
    SOUP("Soup"),
    DESSERT("Dessert"),
    VEGETERIAN("Vegeterian"),
    MILK("Milk"),
    VEGAN("Vegan"),
    PIZZA("Pizza"),
    DONUT("Donut")
}

fun getAllFoodCategories(): List<FoodCategory> {
    return listOf(
        FoodCategory.CHICKEN,
        FoodCategory.BEEF,
        FoodCategory.SOUP,
        FoodCategory.DESSERT,
        FoodCategory.VEGAN,
        FoodCategory.VEGETERIAN,
        FoodCategory.PIZZA,
        FoodCategory.MILK,
        FoodCategory.DONUT
    )
}

fun getFoodCategory(value:String):FoodCategory?{
    val map = FoodCategory.values().associateBy(FoodCategory::value)
    return map[value]
}
