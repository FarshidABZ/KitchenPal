package com.kitchenpal.util

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

/**
 * A useful helper class to retrieve resources
 *
 * @author FarshiABZ on 24.02.2023
 */
interface ResourceManager {
    /**
     * Gets string by resource id
     *
     * @param resId string resource id
     * @return String
     */
    fun getString(@StringRes resId: Int): String
    fun getStringArray(@ArrayRes resId: Int): Array<String>
}

/**
 * Implementation of  [ResourceManager]
 *
 * @property context android context
 */
internal class ResourceManagerImpl constructor(private val context: Context) :
    ResourceManager {
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

    override fun getStringArray(resId: Int): Array<String> {
        return context.resources.getStringArray(resId)
    }
}