package com.ahmedkenawy.cfhtest.domain.use_case.auth_use_case

import com.ahmedkenawy.cfhtest.domain.use_case.auth_use_case.sign_up_use_case.SignUpUseCase
import com.ahmedkenawy.cfhtest.domain.use_case.auth_use_case.sing_in_use_case.SignInUseCase

data class AuthUseCase(val signInUseCase: SignInUseCase, val singUpUseCase: SignUpUseCase)