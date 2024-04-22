package com.example.bookhouse.util

import android.content.Context
import android.util.Log
import com.example.bookhouse.presentation.sign_in.UserData
import com.example.bookhouse.util.converter.fromJson

//fun getUserData(context: Context): UserData? {
//    val dataStoreUtils = DataStoreUtils(context)
//
//    val newValue = ""
//    val jsonData = dataStoreUtils.getUserData("user", newValue)
//
//    Log.d("jsonData", "getUserData: $jsonData")
//
//    if (jsonData != null) {
//        if (jsonData.isNotEmpty()) {
//            val fromJsonData = jsonData.fromJson(UserData::class.java)
//
//            return fromJsonData
//        }
//    }
//    return null
//}