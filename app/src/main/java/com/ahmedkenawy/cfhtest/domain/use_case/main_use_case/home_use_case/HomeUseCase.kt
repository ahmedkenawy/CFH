package com.ahmedkenawy.cfhtest.domain.use_case.main_use_case.home_use_case

import com.ahmedkenawy.cfhtest.domain.model.response.Location
import com.ahmedkenawy.cfhtest.domain.repository.AppRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val appRepository: AppRepository) {

    suspend fun invoke(location: String) = appRepository.getVenues(location)
}
