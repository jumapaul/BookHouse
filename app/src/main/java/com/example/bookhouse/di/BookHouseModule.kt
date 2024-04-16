package com.example.bookhouse.di

import android.content.Context
import com.example.bookhouse.util.DataStoreUtils
import com.google.firebase.auth.FirebaseAuth
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

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

//    @Provides
//    @Singleton
//    fun provideEmailAndPassword(): ValidateEmailAndPassword{
//        return ValidateEmailAndPassword()
//    }

}