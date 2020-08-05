package com.example.shiftstream2.feature.city.list.domain

import com.example.common.CreateCityDto
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType

interface CitiesRepository {

    suspend fun getCities() : List<ItemType>
    suspend fun getPage(start: Long, size: Int) : List<ItemType>
    suspend fun addForecast(forecast: CreateCityDto)
    suspend fun deleteForecast(id: Long)
}