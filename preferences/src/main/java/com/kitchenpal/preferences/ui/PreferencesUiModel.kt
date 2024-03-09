package com.kitchenpal.preferences.ui

import androidx.compose.runtime.Stable
import java.util.UUID

@Stable
data class Chip(
    val title: String,
    var isSelected: Boolean = false,
    val id: String = UUID.randomUUID().toString()
)

data class PreferencesUiModel(
    val title: String,
    val description: String,
    val chips: List<Chip>,
    val seeAll: SeeAll? = SeeAll("See All", UUID.randomUUID().toString()),
    val isMultiSelectAvailable: Boolean = true
)

data class SeeAll(val title: String, val id: String)

fun getFirstPage() = listOf(
    PreferencesUiModel(
        "Dietary Preferences",
        "Help us customize your recipes by sharing your dietary preferences and allergies.",
        getDiets(),
        null
    ),
    PreferencesUiModel(
        "Cuisine Preferences",
        "Share your favorite cuisines and the types of meals you enjoy the most.",
        getCuisines(),
        null
    )
)

fun getSecondPage() = listOf(
    PreferencesUiModel(
        "Allergy & Dietary Restrictions",
        "Customize Your Recipes: Let us know about your allergies and dietary restrictions to tailor recipes to your needs.",
        getAllergies(),
    ),
    PreferencesUiModel(
        "Cooking Skills",
        "Share your cooking expertise with us.",
        getCookingSkills(),
        null,
        isMultiSelectAvailable = false
    )
)

fun getDiets() = listOf(
    Chip("Dairy-Free"),
    Chip("Keto"),
    Chip("Low-Fat"),
    Chip("Low-Carb"),
    Chip("Vegan"),
    Chip("Vegetarian"),
    Chip("Mediterranean"),
    Chip("Gluten-Free"),
)

fun getCuisines() = listOf(
    Chip("Chinese"),
    Chip("Thai"),
    Chip("Japanese"),
    Chip("Italian"),
    Chip("French"),
    Chip("Mexican"),
    Chip("Mediterranean"),
    Chip("Indian"),
    Chip("Middle Eastern"),
    Chip("Spanish Tapas"),
)

fun getAllergies() = listOf(
    Chip("Fish"),
    Chip("Nuts"),
    Chip("Diary"),
    Chip("Soy"),
    Chip("Meat"),
    Chip("Comedy"),
)

fun getCookingSkills() = listOf(
    Chip("Beginner"),
    Chip("Enthusiast"),
    Chip("Advanced")
)