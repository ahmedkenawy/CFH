package com.ahmedkenawy.cfhtest.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * A base ViewModel class for authentication-related operations.
 * Provides common functionality for email and password validation.
 */
open class BaseAuthViewModel : ViewModel() {

    /**
     * LiveData holding the email value.
     */
    val email = MutableLiveData<String>()

    /**
     * LiveData holding the password value.
     */
    val password = MutableLiveData<String>()

    /**
     * LiveData holding the error message for the email field.
     */
    protected val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> get() = _emailError

    /**
     * LiveData holding the error message for the password field.
     */
    protected val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> get() = _passwordError

    /**
     * Validates if the given email is in a correct format.
     *
     * @param email The email string to validate.
     * @return `true` if the email is not null or empty and matches the email pattern; `false` otherwise.
     */
    protected fun isEmailValid(email: String?): Boolean {
        return !email.isNullOrEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * Validates if the given password meets the criteria.
     *
     * Criteria:
     * - Not null or empty.
     * - At least 8 characters long.
     * - Contains at least one digit.
     * - Contains at least one letter.
     * - Contains at least one special character.
     *
     * @param password The password string to validate.
     * @return `true` if the password meets the criteria; `false` otherwise.
     */
    protected fun isPasswordValid(password: String?): Boolean {
        return !password.isNullOrEmpty() && password.length >= 8 &&
                password.any { it.isDigit() } &&
                password.any { it.isLetter() } &&
                password.any { !it.isLetterOrDigit() }
    }
}
