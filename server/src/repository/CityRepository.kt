package com.example.server.repository

import com.example.common.WeatherCity

class CityRepository {

    fun getAll() = listOf(
        WeatherCity("Новосибирск", 20.5),
        WeatherCity("Томск", -32.1)
    )
}