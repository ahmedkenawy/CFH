package com.ahmedkenawy.cfhtest.domain.use_case.auth_use_case.sign_up_use_case

import com.ahmedkenawy.cfhtest.domain.model.User
import com.ahmedkenawy.cfhtest.domain.repository.AppRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val appRepository: AppRepository) {

    suspend fun invoke(
        user: User
    ) = appRepository.saveUserInfo(user)

}