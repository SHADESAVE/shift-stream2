package com.example.shiftstream2.feature.city.list.data

import com.example.common.WeatherCity
import retrofit2.http.GET
import retrofit2.http.POST

interface CitiesApi {
    @GET("/weather/prediction")
    suspend fun getAll(): List<WeatherCity>
}