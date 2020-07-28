package com.example.shiftstream2.feature.city.list.data

import com.example.shiftstream2.feature.city.domain.entity.City
import com.example.shiftstream2.feature.city.domain.entity.NestedItem
import com.example.shiftstream2.feature.city.list.domain.entity.Header
import com.example.shiftstream2.feature.city.list.domain.entity.NestedRecycler
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import java.math.RoundingMode
import kotlin.random.Random

interface NetworkCityDataSource {

    fun getCities() : List<ItemType>
}

class NetworkCityDataSourceImpl: NetworkCityDataSource {

    override fun getCities(): List<ItemType> {

        val rnd = Random
        val itemList = mutableListOf<ItemType>()
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

        itemList.add(
            Header(
                "Погода"
            )
        )

        itemList.add(
            City(
                "Новосибирск",
                (rnd.nextDouble(50.0) - rnd.nextDouble(50.0)).toBigDecimal()
                    .setScale(1, RoundingMode.UP).toDouble()
            )
        )

        itemList.add(
            NestedRecycler(
                nestedList
            )
        )

        itemList.add(
            City(
                "Томск",
                (rnd.nextDouble(50.0) - rnd.nextDouble(50.0)).toBigDecimal()
                    .setScale(1, RoundingMode.UP).toDouble()
            )
        )
        itemList.add(
            NestedRecycler(
                nestedList
            )
        )

        itemList.add(
            Header(
                "Погода в других городах:"
            )
        )

        for (i in 0..50) {
            itemList.add(
                City(
                    "Город $i",
                    (rnd.nextDouble(50.0) - rnd.nextDouble(50.0)).toBigDecimal()
                        .setScale(1, RoundingMode.UP).toDouble()
                )
            )
        }

        return itemList
    }

}