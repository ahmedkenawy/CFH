package com.ahmedkenawy.cfhtest.domain.model.response


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("categoryCode")
    val categoryCode: Int?,
    @SerializedName("icon")
    val icon: Icon?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("mapIcon")
    val mapIcon: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("pluralName")
    val pluralName: String?,
    @SerializedName("primary")
    val primary: Boolean?,
    @SerializedName("shortName")
    val shortName: String?
)