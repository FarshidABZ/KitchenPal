package com.kitchenpal.feature.home

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kitchenpal.core.common.base.BaseResponse
import com.kitchenpal.core.common.sharemodel.data.RecipeResponseModel
import com.kitchenpal.core.common.sharemodel.domain.CategorizedRecipes
import com.kitchenpal.core.common.sharemodel.domain.Category
import com.kitchenpal.core.common.sharemodel.domain.Ingredient
import com.kitchenpal.core.common.sharemodel.domain.NutritionInformation
import com.kitchenpal.core.common.sharemodel.domain.Owner
import com.kitchenpal.core.common.sharemodel.domain.PreparationTime
import com.kitchenpal.core.common.sharemodel.domain.RecipeItemBO
import com.kitchenpal.core.common.sharemodel.domain.Value
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {
    private val _recipes = MutableStateFlow<List<CategorizedRecipes>>(emptyList())
    val recipes: StateFlow<List<CategorizedRecipes>> = _recipes.asStateFlow()

    fun observeRecipes() {
        val db = Firebase.firestore
        val response = db.collection("recipes")
        response.get().addOnSuccessListener {
            val recipeList = mutableListOf<CategorizedRecipes>()
            (it.documents.flatMap { it.data?.values.orEmpty() } as List<String>).forEach { data ->
                Json.decodeFromString<BaseResponse<RecipeResponseModel>>(data).data?.recipe?.let { recipe ->
                    val categorizedRecipesMap = recipe.category?.flatMap { category ->
                        listOf(
                            Category(category.id, category.value) to RecipeItemBO(
                                recipe.title,
                                recipe.name,
                                recipe.id,
                                recipe.shortDescription,
                                recipe.description,
                                ingredients = recipe.ingredients?.map { ingredient ->
                                    Ingredient(
                                        ingredient.title,
                                        ingredient.value?.map { value ->
                                            Value(value.key, value.value)
                                        })
                                },
                                nutritionInformation = recipe.nutritionInformation?.map { nutritionInformation ->
                                    NutritionInformation(
                                        nutritionInformation.key,
                                        nutritionInformation.value
                                    )
                                },
                                recipe.allergyInformation,
                                preparationTime = recipe.preparationTime?.map { preparationTime ->
                                    PreparationTime(preparationTime.key, preparationTime.value)
                                },
                                recipe.instructions,
                                recipe.additionalNotes,
                                recipe.relatedRecipes,
                                category = recipe.category?.map { itemCategory ->
                                    Category(itemCategory.id, itemCategory.value)
                                },
                                recipe.images,
                                recipe.like,
                                owner = Owner(
                                    recipe.owner?.id,
                                    recipe.owner?.imageUrl,
                                    recipe.owner?.name
                                ),
                                recipe.rating,
                                recipe.thumbnail,
                            )
                        )
                    }?.groupBy({ it.first }, { it.second })

                    categorizedRecipesMap?.forEach { (category, recipes) ->
                        val existingCategory = recipeList.find { it.category == category }
                        if (existingCategory != null) {
                            existingCategory.recipes.addAll(recipes)
                        } else {
                            recipeList.add(CategorizedRecipes(category, recipes.toMutableList()))
                        }
                    }
                }
            }

            _recipes.value = recipeList
        }.addOnFailureListener {
        }
    }
}
