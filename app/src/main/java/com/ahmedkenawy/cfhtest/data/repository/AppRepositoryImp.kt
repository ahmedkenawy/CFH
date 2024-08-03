package com.ahmedkenawy.cfhtest.data.repository

import com.ahmedkenawy.cfhtest.data.local.DataStorePreference
import com.ahmedkenawy.cfhtest.data.remote.ApiService
import com.ahmedkenawy.cfhtest.domain.model.User
import com.ahmedkenawy.cfhtest.domain.model.response.VenuesResponse
import com.ahmedkenawy.cfhtest.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of the AppRepository interface, providing data operations.
 *
 * @property dataStorePreference Instance of DataStorePreference for local data storage.
 * @property apiService Instance of ApiService for remote data operations.
 */
class AppRepositoryImp @Inject constructor(
    private val dataStorePreference: DataStorePreference,
    private val apiService: ApiService
) : AppRepository {

    /**
     * Saves user information locally.
     *
     * @param userInfo The user information to save.
     */
    override suspend fun saveUserInfo(userInfo: User) = dataStorePreference.saveUser(userInfo)

    /**
     * Retrieves the user information from local storage.
     *
     * @return A Flow emitting the user information.
     */
    override fun getUserInfo(): Flow<User> {
        return dataStorePreference.userInfo
    }

    /**
     * Fetches venues based on the provided location from the remote API.
     *
     * @param location The location to fetch venues for.
     * @return A Response containing the venues data.
     */
    override suspend fun getVenues(location: String) = apiService.getVenues(location)
}
