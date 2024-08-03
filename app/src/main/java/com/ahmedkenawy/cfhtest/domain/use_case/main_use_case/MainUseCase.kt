package com.ahmedkenawy.cfhtest.domain.use_case.main_use_case

import com.ahmedkenawy.cfhtest.domain.use_case.main_use_case.home_use_case.HomeUseCase
import com.ahmedkenawy.cfhtest.domain.use_case.main_use_case.profile_use_case.ProfileUseCase

data class MainUseCase (
    val profileUseCase: ProfileUseCase,
    val homeUseCase: HomeUseCase,
)