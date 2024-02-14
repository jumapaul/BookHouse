package com.example.bookhouse.on_board.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")

class DataStoreUtils(context: Context) {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")
    }

    private val dataStore = context.dataStore
    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    fun readOnBoardingState(): Flow<Boolean>{
        return dataStore.data.catch {exception->
            if (exception is IOException){
                emit(emptyPreferences())
            }else{
                throw exception
            }
        }.map {preference->
            val onBoardState = preference[PreferencesKey.onBoardingKey]?: false
            onBoardState
        }
    }
}