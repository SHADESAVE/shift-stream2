package com.example.shiftstream2.presentation.viewmodel.weather.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shiftstream2.model.entity.CityItem
import com.example.shiftstream2.model.entity.HeaderItem
import com.example.shiftstream2.model.entity.NestedItem
import com.example.shiftstream2.model.entity.NestedRecycler
import com.example.shiftstream2.presentation.ui.weather.list.ItemType
import com.example.shiftstream2.presentation.viewmodel.SingleLiveEvent
import java.math.RoundingMode
import kotlin.random.Random

class WeatherViewModel : ViewModel() {

    val items = MutableLiveData<List<ItemType>>()
    val itemClickEvent = SingleLiveEvent<Any>()

    init {
        items.value = getList()
    }

    fun itemClicked(itemType: Any) {
        itemClickEvent(itemType)
    }

    private fun getList(): List<ItemType> {

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

        itemList.add(HeaderItem("Погода"))

        itemList.add(
            CityItem(
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
            CityItem(
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

        itemList.add(HeaderItem("Погода в других городах:"))

        for (i in 0..50) {
            itemList.add(
                CityItem(
                    "Город $i",
                    (rnd.nextDouble(50.0) - rnd.nextDouble(50.0)).toBigDecimal()
                        .setScale(1, RoundingMode.UP).toDouble()
                )
            )
        }

        return itemList
    }

}