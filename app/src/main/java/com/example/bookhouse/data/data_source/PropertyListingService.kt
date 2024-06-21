package com.example.bookhouse.data.data_source

import android.content.Context
import com.example.bookhouse.domain.model.PropertyListing
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PropertyListingService(private val context: Context) {
    fun getPropertyListing(resourceId: Int): PropertyListing {
        val json =
            context.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
        val listType = object : TypeToken<PropertyListing>() {}.type
        return Gson().fromJson(json, listType)
    }
}