package com.kitchenpal.core.share.recipe

import androidx.compose.runtime.Stable
import com.kitchenpal.core.common.base.mvibase.MviIntent
import com.kitchenpal.core.common.base.mvibase.MviSingleEvent
import com.kitchenpal.core.common.base.mvibase.MviViewState

@Stable
sealed interface RecipeEvent : MviIntent {
    data class RecipeClicked(val recipeId: String) : RecipeEvent
    data class LikeRecipeClicked(val recipeId: String) : RecipeEvent
    data object LoadRecipes : RecipeEvent
    data object RefreshRecipes : RecipeEvent
}

@Stable
sealed interface SingleEvent : MviSingleEvent {
    data class Failed(val message: String) : SingleEvent
    data class NavigateToRecipeDetail(val recipeId: String, val recipe: Any? = null) : SingleEvent
}

@Stable
data class ViewState(
    val recipes: List<Any> = emptyList(),
    val loading: Boolean = false
) : MviViewState