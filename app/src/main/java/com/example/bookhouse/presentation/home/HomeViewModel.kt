package com.example.bookhouse.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bookhouse.presentation.sign_in.UserData
import com.example.bookhouse.util.DataStoreUtils
import com.example.bookhouse.util.converter.fromJson
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val dataStoreUtils: DataStoreUtils
) : ViewModel() {

    private var _user = mutableStateOf<UserData?>(null)
    var user = _user

    init {
        getUserInfo()
    }
    private fun getUserInfo() {
        val jsonUser = dataStoreUtils.getUserData("user", "")
        val userData = jsonUser.fromJson(UserData::class.java)
        _user.value = userData
    }
}