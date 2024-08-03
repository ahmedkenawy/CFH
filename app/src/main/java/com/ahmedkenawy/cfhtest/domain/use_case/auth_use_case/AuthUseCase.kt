package com.ahmedkenawy.cfhtest.domain.use_case.auth_use_case

import com.ahmedkenawy.cfhtest.domain.use_case.auth_use_case.sign_up_use_case.SignUpUseCase
import com.ahmedkenawy.cfhtest.domain.use_case.auth_use_case.sing_in_use_case.SignInUseCase

/**
 * Container for authentication-related use cases.
 *
 * @property signInUseCase Use case for handling user sign-in operations.
 * @property signUpUseCase Use case for handling user sign-up operations.
 */
data class AuthUseCase(
    val signInUseCase: SignInUseCase,
    val signUpUseCase: SignUpUseCase
)
