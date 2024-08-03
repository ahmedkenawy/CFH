package com.ahmedkenawy.cfhtest.domain.use_case.auth_use_case.sing_in_use_case

import com.ahmedkenawy.cfhtest.domain.repository.AppRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val appRepository: AppRepository) {
    val userInfo = appRepository.getUserInfo()
}