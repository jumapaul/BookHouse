package com.example.bookhouse.presentation.sign_in

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookhouse.domain.model.sign_in.DataProvider
import com.example.bookhouse.domain.model.sign_in.User
import com.example.bookhouse.domain.repository.AuthRepository
import com.example.bookhouse.util.DataStoreUtils
import com.example.bookhouse.util.Resource
import com.example.bookhouse.util.converter.toJson
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val repository: AuthRepository,
    val oneTapClient: SignInClient,
    private val dataStoreUtils: DataStoreUtils
) : ViewModel() {
    var number = MutableStateFlow(0)
    private val _signIn = MutableStateFlow<Resource<User>>(Resource.Loading())
    val signIn = _signIn.asStateFlow()

    private val _resetPassword = MutableStateFlow<Resource<String>>(Resource.Loading())
    val resetPassword = _resetPassword.asStateFlow()

    private var _startDestinationState: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val startDestinationState: StateFlow<Boolean> = _startDestinationState.asStateFlow()

    init {
        getState()
    }

    private fun getState() {
        val newValue = false
        _startDestinationState.value = dataStoreUtils.getFinishState("finish", newValue)
    }
    fun signInWithEmailAndPassword(user: User) {
        viewModelScope.launch {
            firebaseAuth.signInWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener {
                    _signIn.value = Resource.Success(
                        data = user
                    )
                }.addOnFailureListener {
                    _signIn.value = Resource.Error(it.message.toString())
                }
        }

    }

    fun resetPassword(email: String) {
        viewModelScope.launch {
            _resetPassword.emit(Resource.Loading())
        }

        firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener {
            viewModelScope.launch {
                _resetPassword.emit(Resource.Success(email))
            }
        }.addOnFailureListener {
            viewModelScope.launch {
                _resetPassword.emit(Resource.Error(it.message.toString()))
            }
        }
    }

    fun oneTapSignIn() = CoroutineScope(Dispatchers.IO).launch {
        Log.d("------->", "oneTapSignIn: called")
        DataProvider.oneTapSignInResponse = Resource.Loading()
        DataProvider.oneTapSignInResponse = repository.oneTapSignIn()
    }

    fun signInWithGoogle(credential: SignInCredential) = CoroutineScope(Dispatchers.IO).launch {
        DataProvider.googleSignInResponse = Resource.Loading()
        DataProvider.googleSignInResponse = repository.signInWithGoogle(credential)
    }

    fun saveUserData(user: UserData) {
        val jsonUser = user.toJson()
        if (jsonUser != null) {
            dataStoreUtils.saveUserData("user", jsonUser)
        }
    }
}
