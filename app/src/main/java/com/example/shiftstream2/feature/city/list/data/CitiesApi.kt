package com.example.shiftstream2.feature.city.list.data

import com.example.common.CreateCityDto
import com.example.common.WeatherCity
import okhttp3.ResponseBody
import retrofit2.http.*

interface CitiesApi {

    @GET("/weather/prediction")
    suspend fun getAll(): List<WeatherCity>

    @GET("/weather/prediction")
    suspend fun getPage(@Query("start") start: Long, @Query("size") size: Int) : List<WeatherCity>

    @POST("/weather/prediction")
    suspend fun addWeatherForecast(@Body createCityDto: CreateCityDto): ResponseBody

    @DELETE("weather/prediction")
    suspend fun deleteForecast(@Query("id") id: Long): ResponseBody
}