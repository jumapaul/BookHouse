package com.example.bookhouse.presentation.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookhouse.domain.model.PropertyResults
import com.example.bookhouse.domain.repository.PropertyListingRepository
import com.example.bookhouse.presentation.sign_in.UserData
import com.example.bookhouse.presentation.states.PropertyListingUiState
import com.example.bookhouse.util.DataStoreUtils
import com.example.bookhouse.util.converter.fromJson
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val dataStoreUtils: DataStoreUtils,
    private val repository: PropertyListingRepository
//    private val homeUseCases: HomeUseCases
) : ViewModel() {

    private val properties: MutableList<PropertyResults> = mutableListOf()

    private var _propertiesListingState = mutableStateOf(PropertyListingUiState())
    val propertyListingState: State<PropertyListingUiState> = _propertiesListingState
    private var _user = mutableStateOf<UserData?>(null)
    var user = _user

    private var _isAuthenticated = MutableStateFlow<Boolean?>(true)
    var isAuthenticated = _isAuthenticated

    var propertiesGroup = MutableStateFlow<Map<String, List<PropertyResults>>>(emptyMap())

    init {
        getUserInfo()
        getUserNameFromEmail()
        getCurrentUser()
        getPropertyListings()
    }

    private fun getUserInfo() {
        val jsonUser = dataStoreUtils.getUserData("user", "")
        val userData = jsonUser.fromJson(UserData::class.java)
        _user.value = userData
    }

    private fun getUserNameFromEmail() {
        val email = firebaseAuth.currentUser?.email

        val name = email?.substringBefore("@")
        val profilePicUrl = ""
        _user.value = UserData(name, profilePicUrl)
    }

    private fun getCurrentUser() {
        _isAuthenticated.value = firebaseAuth.currentUser != null
    }

    private fun getPropertyListings() = viewModelScope.launch {
        repository.getAllListingProperties().collect {
            properties.clear()
            properties.addAll(it)
            _propertiesListingState.value = _propertiesListingState.value.copy(
                data = it
            )


            //types
            val properties = _propertiesListingState.value.data?.groupBy { p -> p.property_type }

            if (properties != null) {
                propertiesGroup.value = properties
            }
        }
    }

    fun getListingGroups(groupName: String): List<PropertyResults>? {
        return propertiesGroup.value[groupName]
    }
}