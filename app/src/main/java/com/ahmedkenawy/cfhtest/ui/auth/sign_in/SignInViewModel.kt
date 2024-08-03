package com.ahmedkenawy.cfhtest.ui.auth.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmedkenawy.cfhtest.base.BaseAuthViewModel
import com.ahmedkenawy.cfhtest.domain.use_case.auth_use_case.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val authUseCase: AuthUseCase) : BaseAuthViewModel() {

    private val _isSignInSuccessful = MutableLiveData<Boolean>()
    val isSignInSuccessful: LiveData<Boolean> get() = _isSignInSuccessful

    fun validateAndSignIn() {
        var isValid = true

        if (!isEmailValid(email.value)) {
            _emailError.value = "Invalid email"
            isValid = false
        } else {
            _emailError.value = null
        }

        if (!isPasswordValid(password.value)) {
            _passwordError.value = "Password must be 8 characters long with Alphanumeric and Special Characters"
            isValid = false
        } else {
            _passwordError.value = null
        }

        if (isValid) {
            viewModelScope.launch {
                val storedUser = authUseCase.signInUseCase.userInfo.first()
                if (storedUser.email == email.value && storedUser.password == password.value) {
                    _isSignInSuccessful.value = true
                } else {
                    _emailError.value = "Invalid email or password"
                    _passwordError.value = "Invalid email or password"
                }
            }
        }
    }
}

