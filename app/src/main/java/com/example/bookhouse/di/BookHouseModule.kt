package com.example.bookhouse.di

import android.content.Context
import com.example.bookhouse.on_board.utils.DataStoreUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookHouseModule {

    @Provides
    @Singleton
    fun provideDataUtils(
        @ApplicationContext context: Context
    ) = DataStoreUtils(context = context)
}