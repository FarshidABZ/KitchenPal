package com.kitchenpal.di

import android.content.Context
import com.kitchenpal.util.ResourceManager
import com.kitchenpal.util.ResourceManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * provide object for all application
 *
 * @author FarshidABZ on 24.02.2023
 */
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideResourceManager(@ApplicationContext appContext: Context): ResourceManager =
        ResourceManagerImpl(appContext)
}