package com.vunh.demo_clean_app.data.common.module

import android.content.Context
import com.vunh.demo_clean_app.utils.SharedPreferencesStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {

    @Provides
    fun provideSharedPref(@ApplicationContext context: Context) : SharedPreferencesStorage{
        return SharedPreferencesStorage(context)
    }
}