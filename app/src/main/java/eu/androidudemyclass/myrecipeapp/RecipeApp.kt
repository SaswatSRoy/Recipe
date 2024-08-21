package eu.androidudemyclass.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.state

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.routes) {
        composable(route = Screen.RecipeScreen.routes) {
            RecipeScreen( viewState =viewState,navigateToDetail = {

                navController.currentBackStackEntry?.savedStateHandle?.set("category",it)
                navController.navigate(Screen.DetailScreen.routes)
            } )

        }
        composable(route = Screen.DetailScreen.routes) {
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("category")?:Category("","","","")
            CategoryScreenDetails(category = category)
            navController.navigate(Screen.RecipeScreen.routes)

        }
    }
}