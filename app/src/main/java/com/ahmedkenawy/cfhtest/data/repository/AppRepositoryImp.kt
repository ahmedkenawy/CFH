package com.ahmedkenawy.cfhtest.data.repository

import com.ahmedkenawy.cfhtest.data.local.DataStorePreference
import com.ahmedkenawy.cfhtest.data.remote.ApiService
import com.ahmedkenawy.cfhtest.domain.model.User
import com.ahmedkenawy.cfhtest.domain.model.response.VenuesResponse
import com.ahmedkenawy.cfhtest.domain.repository.AppRepository
import com.ahmedkenawy.cfhtest.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepositoryImp @Inject constructor(
    private val dataStorePreference: DataStorePreference,
    private val apiService: ApiService
) : AppRepository {

    override suspend fun saveUserInfo(userInfo: User) = dataStorePreference.saveUser(userInfo)

    override fun getUserInfo(): Flow<User> {
        return dataStorePreference.userInfo
    }

    override suspend fun getVenues(location: String) = apiService.getVenues(location)

}