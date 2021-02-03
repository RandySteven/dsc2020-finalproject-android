package com.example.covidtracker.province

import com.example.covidtracker.province.datamodel.GetProvinceResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api")
    fun getData(): Call<GetProvinceResponse>

    @GET("api/provinsi")
    fun getProvince(): Call<GetProvinceResponse>
}