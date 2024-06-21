package com.example.bookhouse.data.repositoryImpl

import android.util.Log
import com.example.bookhouse.R
import com.example.bookhouse.data.data_source.PropertyListingService
import com.example.bookhouse.domain.model.PropertyResults
import com.example.bookhouse.domain.repository.PropertyListingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PropertiesListingRepositoryImpl @Inject constructor(
    propertyListingService: PropertyListingService
) : PropertyListingRepository {

    private var properties = mutableListOf<PropertyResults>()

    init {
        propertyListingService.getPropertyListing(R.raw.listings).results
        Log.d("----------Results", ": ${propertyListingService.getPropertyListing(R.raw.listings).results}")
        properties.addAll(propertyListingService.getPropertyListing(R.raw.listings).results)
    }

    override suspend fun getAllListingProperties(): Flow<List<PropertyResults>> = flow {
        emit(properties)
    }
}