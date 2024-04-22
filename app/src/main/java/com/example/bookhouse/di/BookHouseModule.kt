package com.example.bookhouse.di

import android.app.Application
import android.content.Context
import com.example.bookhouse.R
import com.example.bookhouse.constants.Constants.SIGN_IN_REQUEST
import com.example.bookhouse.constants.Constants.SIGN_UP_REQUEST
import com.example.bookhouse.data.repositoryImpl.AuthRepositoryImpl
import com.example.bookhouse.domain.repository.AuthRepository
import com.example.bookhouse.util.DataStoreUtils
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookHouseModule {

    @Provides
    @Singleton
    @Named
    fun provideDataUtils(
        @ApplicationContext context: Context
    ) = DataStoreUtils(context = context)
    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    fun provideOneTapClient(@ApplicationContext context: Context) =
        Identity.getSignInClient(context)

    @Provides
    @Named(SIGN_IN_REQUEST)
    fun providesSignInRequest(
        application: Application
    ) = BeginSignInRequest.Builder().setGoogleIdTokenRequestOptions(
        BeginSignInRequest.GoogleIdTokenRequestOptions.builder().setSupported(true)
            .setServerClientId(
                application.getString(
                    R.string.web_client_id
                )
            ).setFilterByAuthorizedAccounts(true).build()
    ).setAutoSelectEnabled(true).build()

    @Provides
    @Named(SIGN_UP_REQUEST)
    fun providesSignUpRequest(
        application: Application
    ) = BeginSignInRequest.Builder().setGoogleIdTokenRequestOptions(
        BeginSignInRequest.GoogleIdTokenRequestOptions.builder().setSupported(true)
            .setServerClientId(application.getString(R.string.web_client_id))
            .setFilterByAuthorizedAccounts(false).build()
    ).build()

    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}