package com.ahmedkenawy.cfhtest.domain.use_case.main_use_case.profile_use_case

import com.ahmedkenawy.cfhtest.domain.repository.AppRepository
import javax.inject.Inject

/**
 * Use case for handling profile-related operations.
 *
 * @property appRepository Repository for accessing application data.
 */
class ProfileUseCase @Inject constructor(private val appRepository: AppRepository) {
    /**
     * Live data or flow for retrieving user information.
     */
    val userInfo = appRepository.getUserInfo()
}
