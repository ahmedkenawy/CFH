package com.ahmedkenawy.cfhtest.domain.model.response


import com.google.gson.annotations.SerializedName

data class Venue(
    @SerializedName("categories")
    val categories: List<Category?>?,
    @SerializedName("createdAt")
    val createdAt: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("name")
    val name: String?
)