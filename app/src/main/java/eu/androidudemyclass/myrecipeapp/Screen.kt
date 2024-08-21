package eu.androidudemyclass.myrecipeapp

sealed class Screen(val routes: String) {
    data object RecipeScreen: Screen("recipescreen")
    data object DetailScreen: Screen("detailsscreen")
}