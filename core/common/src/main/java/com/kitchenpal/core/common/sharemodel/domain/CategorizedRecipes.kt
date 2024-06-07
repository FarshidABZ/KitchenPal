package com.kitchenpal.core.common.sharemodel.domain

data class CategorizedRecipes(
    val category: Category,
    val recipes: MutableList<RecipeItemBO>
)

data class RecipeItemBO(
    val title: String? = null,
    val name: String? = null,
    val id: String? = null,
    val shortDescription: String? = null,
    val description: String? = null,
    val ingredients: List<Ingredient>? = null,
    val nutritionInformation: List<NutritionInformation>? = null,
    val allergyInformation: String? = null,
    val preparationTime: List<PreparationTime>? = null,
    val instructions: List<String>? = null,
    val additionalNotes: String? = null,
    val relatedRecipes: List<String>? = null,
    val category: List<Category>? = null,
    val images: List<String>? = null,
    val like: Long? = null,
    val owner: Owner? = null,
    val rating: Double? = null,
    val thumbnail: String? = null,
)

data class Value(
    val key: String? = null,
    val value: String? = null,
)

data class Ingredient(
    val title: String? = null,
    val value: List<Value>? = null,
)

data class NutritionInformation(
    val key: String? = null,
    val value: String? = null,
)

data class PreparationTime(
    val key: String? = null,
    val value: String? = null,
)

data class Category(
    val id: String? = null,
    val value: String? = null,
)

data class Owner(
    val id: String? = null,
    val imageUrl: String? = null,
    val name: String? = null,
)

