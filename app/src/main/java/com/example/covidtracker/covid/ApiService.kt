package com.example.covidtracker.covid

import com.example.covidtracker.covid.model.Covid
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api")
    fun getData(): Call<Covid>
}