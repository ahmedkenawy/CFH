package com.ahmedkenawy.cfhtest.data.remote

import com.ahmedkenawy.cfhtest.BuildConfig
import com.ahmedkenawy.cfhtest.domain.model.response.VenuesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface defining the API service for fetching venues.
 */
interface ApiService {

    /**
     * Fetches venues based on the provided location from the remote API.
     *
     * @param location The latitude and longitude of the location (format: "lat,lng").
     * @param clientId The client ID for authentication with the Foursquare API.
     * @param clientSecret The client secret for authentication with the Foursquare API.
     * @param version The version of the Foursquare API.
     * @return A Response containing the venues data.
     */
    @GET("venues/search")
    suspend fun getVenues(
        @Query("ll") location: String,
        @Query("client_id") clientId: String = BuildConfig.FOURSQUARE_CLIENT_ID,
        @Query("client_secret") clientSecret: String = BuildConfig.FOURSQUARE_CLIENT_SECRET,
        @Query("v") version: String = BuildConfig.FOURSQUARE_VERSION
    ): Response<VenuesResponse>
}
