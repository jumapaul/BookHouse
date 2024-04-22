package com.example.bookhouse.presentation.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookhouse.domain.model.User
import com.example.bookhouse.presentation.sign_up.state.RegisterFieldState
import com.example.bookhouse.presentation.sign_up.state.RegisterValidation
import com.example.bookhouse.presentation.sign_up.util.validateConfirmPassword
import com.example.bookhouse.presentation.sign_up.util.validateEmail
import com.example.bookhouse.presentation.sign_up.util.validatePassword
import com.example.bookhouse.util.DataStoreUtils
import com.example.bookhouse.util.Resource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val dataStoreUtils: DataStoreUtils,
) : ViewModel() {

    private val _register = MutableStateFlow<Resource<User>>(Resource.Loading())
    val register = _register.asStateFlow()

    private val _validation = Channel<RegisterFieldState>()
    val validated = _validation.receiveAsFlow()

    private var _startDestinationState: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val startDestinationState: StateFlow<Boolean> = _startDestinationState.asStateFlow()

    init {
        getState()
    }
    private fun getState(): Boolean {
        val newValue = false
        return dataStoreUtils.getFinishState("finish", newValue)
    }

    fun createAccountWithEmailAndPassword(user: User, confirmPassword: String) {
        viewModelScope.launch {
            if (checkValidation(user, confirmPassword)) {
                _register.emit(Resource.Loading())

                firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
                    .addOnSuccessListener {
                        _register.value = Resource.Success(
                            data = user
                        )
                    }
                    .addOnFailureListener {
                        _register.value = Resource.Error(it.message.toString())
                    }
            } else {
                val registerFieldState = RegisterFieldState(
                    validateEmail(user.email) as RegisterValidation,
                    validatePassword(user.password) as RegisterValidation
                )

                _validation.send(registerFieldState)

            }
        }
    }

    private fun checkValidation(user: User, confirmPassword: String): Boolean {
        val emailValidation = validateEmail(user.email)
        val passwordValidation = validatePassword(user.password)
        val confirmPasswordValidation = validateConfirmPassword(user.password, confirmPassword)

        return emailValidation is RegisterValidation.Success && confirmPasswordValidation is RegisterValidation.Success && passwordValidation is RegisterValidation.Success
    }

}