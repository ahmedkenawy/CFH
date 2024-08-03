package com.ahmedkenawy.cfhtest.domain.model.response


import com.google.gson.annotations.SerializedName

data class VenuesResponse(
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("response")
    val response: Response?
)