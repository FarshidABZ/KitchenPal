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

}
