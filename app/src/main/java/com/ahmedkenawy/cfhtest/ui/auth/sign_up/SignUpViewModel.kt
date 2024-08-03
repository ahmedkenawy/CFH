package com.ahmedkenawy.cfhtest.ui.auth.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmedkenawy.cfhtest.base.BaseAuthViewModel
import com.ahmedkenawy.cfhtest.domain.model.User
import com.ahmedkenawy.cfhtest.domain.use_case.auth_use_case.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SignUpViewModel @Inject constructor(private val authUseCase: AuthUseCase) : BaseAuthViewModel() {

    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val age = MutableLiveData<String>()

    private val _firstNameError = MutableLiveData<String?>()
    val firstNameError: LiveData<String?> get() = _firstNameError

    private val _lastNameError = MutableLiveData<String?>()
    val lastNameError: LiveData<String?> get() = _lastNameError

    private val _ageError = MutableLiveData<String?>()
    val ageError: LiveData<String?> get() = _ageError

    private val _isRegistrationSuccessful = MutableLiveData<Boolean>()
    val isRegistrationSuccessful: LiveData<Boolean> get() = _isRegistrationSuccessful

    fun validateAndRegister() {
        var isValid = true

        if (firstName.value.isNullOrEmpty()) {
            _firstNameError.value = "First name is required"
            isValid = false
        } else {
            _firstNameError.value = null
        }

        if (lastName.value.isNullOrEmpty()) {
            _lastNameError.value = "Last name is required"
            isValid = false
        } else {
            _lastNameError.value = null
        }

        val ageInt = age.value?.toIntOrNull()
        if (ageInt == null || ageInt < 18) {
            _ageError.value = "Age must be 18 or above"
            isValid = false
        } else {
            _ageError.value = null
        }

        if (!isEmailValid(email.value)) {
            _emailError.value = "Invalid email"
            isValid = false
        } else {
            _emailError.value = null
        }

        if (!isPasswordValid(password.value)) {
            _passwordError.value =
                "Password must be 8 characters long with Alphanumeric and Special Characters"
            isValid = false
        } else {
            _passwordError.value = null
        }

        if (isValid) {
            val user = User(
                firstName.value.orEmpty(),
                lastName.value.orEmpty(),
                ageInt ?: 0,
                email.value.orEmpty(),
                password.value.orEmpty()
            )
            viewModelScope.launch {
                authUseCase.singUpUseCase.invoke(user)
                _isRegistrationSuccessful.value = true
            }
        }
    }
}

