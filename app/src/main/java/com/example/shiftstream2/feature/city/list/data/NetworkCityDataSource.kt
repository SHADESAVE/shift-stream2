package com.example.shiftstream2.feature.city.list.data

import android.util.Log
import com.example.common.CreateCityDto
import com.example.shiftstream2.feature.city.domain.entity.NestedItem
import com.example.shiftstream2.feature.city.list.presentation.adapters.Header
import com.example.shiftstream2.feature.city.list.presentation.adapters.NestedRecycler
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import com.example.shiftstream2.feature.utils.toCity

interface NetworkCityDataSource {

    suspend fun getCities(): List<ItemType>
    suspend fun getPage(start: Long, size: Int): List<ItemType>
    suspend fun addForecast(forecast: CreateCityDto)
    suspend fun deleteForecast(id: Long)
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
        itemList.add(Header("Погода"))
        itemList.add(NestedRecycler(nestedList))
        itemList.addAll(cities)
        itemList.add(Header("Погода в городах:"))
        itemList.add(NestedRecycler(nestedList))
        return itemList
    }

    override suspend fun getPage(start: Long, size: Int): List<ItemType> {
        val cities = api.getPage(start, size).map { it.toCity() }
        Log.d("Page: ", "$cities")
        if (cities.isEmpty())
            return emptyList()
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
        itemList.add(Header("Погода в городах:"))
        itemList.add(NestedRecycler(nestedList))
        itemList.addAll(cities)
        return itemList
    }

    override suspend fun addForecast(forecast: CreateCityDto) {
        api.addWeatherForecast(forecast)
    }


    override suspend fun deleteForecast(id: Long) {
        api.deleteForecast(id)
    }

}