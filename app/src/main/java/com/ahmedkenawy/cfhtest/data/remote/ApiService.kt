package com.ahmedkenawy.cfhtest.data.remote

import com.ahmedkenawy.cfhtest.BuildConfig
import com.ahmedkenawy.cfhtest.domain.model.response.VenuesResponse
import com.ahmedkenawy.cfhtest.utils.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("venues/search")
    suspend fun getVenues(
        @Query("ll") location: String,
        @Query("client_id") clientId: String= BuildConfig.FOURSQUARE_CLIENT_ID,
        @Query("client_secret") clientSecret: String=BuildConfig.FOURSQUARE_CLIENT_SECRET,
        @Query("v") version: String =BuildConfig.FOURSQUARE_VERSION
    ): Response<VenuesResponse>
}