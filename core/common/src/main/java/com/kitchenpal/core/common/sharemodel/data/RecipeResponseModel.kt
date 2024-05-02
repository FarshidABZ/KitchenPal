package com.kitchenpal.core.common.sharemodel.data

import com.kitchenpal.core.common.base.ResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponseModel(val recipe: Recipe) : ResponseModel

@Serializable
data class Recipe(
    val title: String,
    val name: String,
    val id: String,
    @SerialName("short_description")
    val shortDescription: String,
    val description: String,
    val ingredients: List<Ingredient>,
    @SerialName("nutrition_information")
    val nutritionInformation: List<NutritionInformation>,
    @SerialName("allergy_information")
    val allergyInformation: String,
    @SerialName("preparation_time")
    val preparationTime: List<PreparationTime>,
    val instructions: List<String>,
    @SerialName("additional_notes")
    val additionalNotes: String,
    @SerialName("related_recipes")
    val relatedRecipes: List<String>,
    val category: List<Category>,
    val images: List<String>,
    val like: Long,
    val owner: Owner,
    @SerialName("Rating")
    val rating: Double,
    val thumbnail: String,
) : ResponseModel

@Serializable
data class Ingredient(
    val title: String,
    val value: List<Value>,
)

@Serializable
data class Value(
    val key: String,
    val value: String,
)

@Serializable
data class NutritionInformation(
    val key: String,
    val value: String,
)

@Serializable
data class PreparationTime(
    val key: String,
    val value: String,
)

@Serializable
data class Category(
    val id: String,
    val value: String,
)

@Serializable
data class Owner(
    val id: String,
    @SerialName("image_url")
    val imageUrl: String,
    val name: String,
)

