package com.example.bookhouse.util

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataStoreUtils @Inject constructor(@ApplicationContext context: Context) {

    private val finishSharedPreferences: SharedPreferences =
        context.getSharedPreferences("finish", Context.MODE_PRIVATE)

    private val userSharedPreferences: SharedPreferences =
        context.getSharedPreferences("user", Context.MODE_PRIVATE)

    fun saveFinishState(key: String, value: Boolean) {
        val editor = finishSharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getFinishState(key: String, defaultValue: Boolean): Boolean {
        return finishSharedPreferences.getBoolean(key, defaultValue)
    }

    fun saveUserData(key: String, value: String) {
        val editor = userSharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getUserData(key: String, defaultValue: String): String {
        return userSharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
}