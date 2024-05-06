package com.kitchenpal.feature.home.domain.repository

import com.kitchenpal.core.common.result.Result
import com.kitchenpal.core.common.sharemodel.data.RecipeResponseModel

interface RecipeListRepository {
    fun getRecipeList() : Result<RecipeResponseModel>
}