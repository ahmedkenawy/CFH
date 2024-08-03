package com.ahmedkenawy.cfhtest.domain.use_case.main_use_case.home_use_case

import com.ahmedkenawy.cfhtest.domain.repository.AppRepository
import javax.inject.Inject

/**
 * Use case for handling home-related operations, such as fetching venues based on location.
 *
 * @property appRepository Repository for accessing application data.
 */
class HomeUseCase @Inject constructor(private val appRepository: AppRepository) {

    /**
     * Fetches venues based on the provided location.
     *
     * @param location The location to fetch venues for.
     * @return Result of the venue fetching operation.
     */
    suspend fun invoke(location: String) = appRepository.getVenues(location)
}
