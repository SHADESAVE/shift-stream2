package com.example.shiftstream2.feature.city.list.data

import com.example.shiftstream2.feature.city.domain.entity.NestedItem
import com.example.shiftstream2.feature.city.list.domain.entity.Header
import com.example.shiftstream2.feature.city.list.domain.entity.NestedRecycler
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import com.example.shiftstream2.feature.utils.toCity

interface NetworkCityDataSource {

    suspend fun getCities() : List<ItemType>
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

}