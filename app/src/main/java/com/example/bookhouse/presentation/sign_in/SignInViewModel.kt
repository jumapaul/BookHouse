package com.example.bookhouse.presentation.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookhouse.domain.model.User
import com.example.bookhouse.util.Resource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _signIn = MutableStateFlow<Resource<User>>(Resource.Loading())
    val signIn = _signIn.asStateFlow()

    private val _resetPassword = MutableStateFlow<Resource<String>>(Resource.Loading())
    val resetPassword = _resetPassword.asStateFlow()

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
}
