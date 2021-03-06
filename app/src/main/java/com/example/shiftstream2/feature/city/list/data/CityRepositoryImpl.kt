package com.example.shiftstream2.feature.city.list.data

import com.example.common.CreateCityDto
import com.example.shiftstream2.feature.city.list.domain.CitiesRepository
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType

class CityRepositoryImpl(
    private val networkCityDataSource: NetworkCityDataSource
) : CitiesRepository {

    override suspend fun getCities(): List<ItemType> = networkCityDataSource.getCities()
    override suspend fun getPage(start: Long, size: Int): List<ItemType> = networkCityDataSource.getPage(start, size)
    override suspend fun addForecast(forecast: CreateCityDto) = networkCityDataSource.addForecast(forecast)
    override suspend fun deleteForecast(id: Long) = networkCityDataSource.deleteForecast(id)
}