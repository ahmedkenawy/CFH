package com.ahmedkenawy.cfhtest.domain.repository

import com.ahmedkenawy.cfhtest.domain.model.User
import com.ahmedkenawy.cfhtest.domain.model.response.VenuesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Interface defining methods for data operations in the application.
 */
interface AppRepository {

    /**
     * Saves user information.
     *
     * @param userInfo The user information to save.
     */
    suspend fun saveUserInfo(userInfo: User)

    /**
     * Retrieves the user information.
     *
     * @return A Flow emitting the user information.
     */
    fun getUserInfo(): Flow<User>

    /**
     * Fetches venues based on the provided location.
     *
     * @param location The location to fetch venues for.
     * @return A Response containing the venues data.
     */
    suspend fun getVenues(location: String): Response<VenuesResponse>
}
