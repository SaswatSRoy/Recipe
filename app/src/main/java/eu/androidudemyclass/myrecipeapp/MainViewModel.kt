package eu.androidudemyclass.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    private val _state = mutableStateOf(RecipeState())
    val state: State<RecipeState> = _state

    init {
        fetchCategories()
    }


    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response= recipeService.getCategories()
                _state.value=_state.value.copy(
                    recipes = response.categories,
                    isLoading = false,
                    error=null
                )


            }catch (e:Exception){
                _state.value=state.value.copy(
                    isLoading = false,
                    error = "Error fetching categories ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val isLoading: Boolean = false,
        val recipes: List<Category> = emptyList(),
        val error: String? = null
    )
}