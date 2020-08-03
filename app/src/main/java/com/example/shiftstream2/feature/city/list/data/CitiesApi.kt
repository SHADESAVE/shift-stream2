package com.example.shiftstream2.feature.city.list.data

import com.example.common.CreateCityDto
import com.example.common.WeatherCity
import retrofit2.http.*

interface CitiesApi {
    @GET("/weather/prediction")
    suspend fun getAll(): List<WeatherCity>

    @POST("weather/prediction")
    fun addWeatherPrediction(
        @Body createCityDto: CreateCityDto
    )

    @DELETE("weather/prediction{id}")
    fun getDeletePrediction(@Path("id") id: Int)
}