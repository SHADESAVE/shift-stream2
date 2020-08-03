package com.example.shiftstream2.feature.city.list.data

import com.example.shiftstream2.feature.city.list.domain.CitiesRepository
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType

class CityRepositoryImpl(
    private val networkCityDataSource: NetworkCityDataSource
) : CitiesRepository {

    override suspend fun getCities(): List<ItemType> = networkCityDataSource.getCities()
}