package com.example.bookhouse.domain.repository

import com.example.bookhouse.domain.model.PropertyResults
import kotlinx.coroutines.flow.Flow

interface PropertyListingRepository {
    suspend fun getAllListingProperties(): Flow<List<PropertyResults>>
}