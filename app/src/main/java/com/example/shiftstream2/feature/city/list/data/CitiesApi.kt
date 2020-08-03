package com.example.shiftstream2.feature.city.list.data

import com.example.common.CreateCityDto
import com.example.common.WeatherCity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface CitiesApi {

    @GET("/weather/prediction")
    suspend fun getAll(): List<WeatherCity>

    @POST("/weather/prediction")
    suspend fun addWeatherPrediction(@Body createCityDto: CreateCityDto): ResponseBody

    @DELETE("weather/prediction{id}")
    fun getDeletePrediction(@Path("id") id: Int)
}