package com.kitchenpal.core.common.sharemodel.data

import com.kitchenpal.core.common.base.ResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponseModel(val recipe: Recipe) : ResponseModel

@Serializable
data class Recipe(
    val title: String? = null,
    val name: String? = null,
    val id: String? = null,
    @SerialName("short_description")
    val shortDescription: String? = null,
    val description: String? = null,
    val ingredients: List<Ingredient>? = null,
    @SerialName("nutrition_information")
    val nutritionInformation: List<NutritionInformation>? = null,
    @SerialName("allergy_information")
    val allergyInformation: String? = null,
    @SerialName("preparation_time")
    val preparationTime: List<PreparationTime>? = null,
    val instructions: List<String>? = null,
    @SerialName("additional_notes")
    val additionalNotes: String? = null,
    @SerialName("related_recipes")
    val relatedRecipes: List<String>? = null,
    val category: List<Category>? = null,
    val images: List<String>? = null,
    val like: Long? = null,
    val owner: Owner? = null,
    @SerialName("Rating")
    val rating: Double? = null,
    val thumbnail: String? = null,
) : ResponseModel

@Serializable
data class Ingredient(
    val title: String? = null,
    val value: List<Value>? = null,
)

@Serializable
data class Value(
    val key: String? = null,
    val value: String? = null,
)

@Serializable
data class NutritionInformation(
    val key: String? = null,
    val value: String? = null,
)

@Serializable
data class PreparationTime(
    val key: String? = null,
    val value: String? = null,
)

@Serializable
data class Category(
    val id: String? = null,
    val value: String? = null,
)

@Serializable
data class Owner(
    val id: String? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    val name: String? = null,
)

