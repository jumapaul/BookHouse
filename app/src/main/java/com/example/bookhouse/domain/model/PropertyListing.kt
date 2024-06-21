package com.example.bookhouse.domain.model

data class PropertyListing(
    val results: List<PropertyResults>,
    val total_count: Int
)