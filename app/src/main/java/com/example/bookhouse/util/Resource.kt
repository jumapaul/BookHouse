package com.example.bookhouse.util

import com.example.bookhouse.domain.model.PropertyResults
import kotlinx.coroutines.flow.Flow

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String) : Resource<T>(message = message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}