package com.ahmedkenawy.cfhtest.domain.use_case.main_use_case

import com.ahmedkenawy.cfhtest.domain.use_case.main_use_case.home_use_case.HomeUseCase
import com.ahmedkenawy.cfhtest.domain.use_case.main_use_case.profile_use_case.ProfileUseCase

/**
 * Main use case class that aggregates multiple use cases related to the main features of the application.
 *
 * @property profileUseCase Use case handling operations related to the user profile.
 * @property homeUseCase Use case handling operations related to the home screen.
 */
data class MainUseCase (
    val profileUseCase: ProfileUseCase,
    val homeUseCase: HomeUseCase,
)
