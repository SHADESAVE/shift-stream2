package com.example.shiftstream2.feature.city.list.data

import com.example.common.CreateCityDto
import com.example.shiftstream2.feature.city.domain.entity.NestedItem
import com.example.shiftstream2.feature.city.list.presentation.adapters.Header
import com.example.shiftstream2.feature.city.list.presentation.adapters.NestedRecycler
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import com.example.shiftstream2.feature.utils.toCity

interface NetworkCityDataSource {

    suspend fun getCities() : List<ItemType>
    suspend fun addForecast(forecast: CreateCityDto)
}

class NetworkCityDataSourceImpl(private val api: CitiesApi) : NetworkCityDataSource {

    override suspend fun getCities(): List<ItemType> {

        val cities = api.getAll().map { it.toCity() }

        val nestedList = mutableListOf<NestedItem>()
        for (i in 1..25) {
            nestedList.add(
                NestedItem(
                    i.toLong(),
                    null,
                    "День $i"
                )
            )
        }

        val itemList = mutableListOf<ItemType>()

        itemList.add(
            Header(
                "Погода"
            )
        )

        itemList.add(
            NestedRecycler(
                nestedList
            )
        )

        itemList.addAll(cities)

        itemList.add(
            Header(
                "Погода в других городах:"
            )
        )

        itemList.add(
            NestedRecycler(
                nestedList
            )
        )

        return itemList
    }

    override suspend fun addForecast(forecast: CreateCityDto) {
        api.addWeatherPrediction(forecast)
    }

}