package com.example.bookhouse.presentation.states

import com.example.bookhouse.domain.model.PropertyResults

data class PropertyListingUiState(
    var isLoading: Boolean = false,
    var data: List<PropertyResults>? = emptyList(),
    var error: String = ""
)
