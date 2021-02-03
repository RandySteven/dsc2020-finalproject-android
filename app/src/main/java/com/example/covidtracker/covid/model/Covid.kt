package com.example.covidtracker.covid.model

import com.google.gson.annotations.SerializedName

data class Covid(
    @SerializedName("confirmed")
    val confirmed: Confirmed,
    @SerializedName("recovered")
    val recovered: Recovered,
    @SerializedName("deaths")
    val deaths: Deaths
)

data class Confirmed(
    @SerializedName("value")
    val value: Int,
    @SerializedName("detail")
    val detail: String
)

data class Recovered(
    @SerializedName("value")
    val value: Int,
    @SerializedName("detail")
    val detail: String
)

data class Deaths(
    @SerializedName("value")
    val value: Int,
    @SerializedName("detail")
    val detail: String
)