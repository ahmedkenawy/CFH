package com.ahmedkenawy.cfhtest.domain.use_case.main_use_case.profile_use_case

import com.ahmedkenawy.cfhtest.domain.repository.AppRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val appRepository: AppRepository) {
    val userInfo = appRepository.getUserInfo()
}