package com.ahmedkenawy.cfhtest.domain.repository

import com.ahmedkenawy.cfhtest.domain.model.User
import com.ahmedkenawy.cfhtest.domain.model.response.Location
import com.ahmedkenawy.cfhtest.domain.model.response.VenuesResponse
import com.ahmedkenawy.cfhtest.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AppRepository {

    suspend fun saveUserInfo(userInfo: User)

    fun getUserInfo(): Flow<User>

    suspend fun getVenues(location: String): Response<VenuesResponse>
}