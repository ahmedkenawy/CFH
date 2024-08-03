package com.ahmedkenawy.cfhtest.domain.use_case.auth_use_case.sign_up_use_case

import com.ahmedkenawy.cfhtest.domain.model.User
import com.ahmedkenawy.cfhtest.domain.repository.AppRepository
import javax.inject.Inject

/**
 * Use case for handling user sign-up operations.
 *
 * @property appRepository Repository for accessing and storing user data.
 */
class SignUpUseCase @Inject constructor(private val appRepository: AppRepository) {

    /**
     * Saves the user information to the repository.
     *
     * @param user The user information to be saved.
     * @return Result of the save operation.
     */
    suspend fun invoke(user: User) = appRepository.saveUserInfo(user)
}
