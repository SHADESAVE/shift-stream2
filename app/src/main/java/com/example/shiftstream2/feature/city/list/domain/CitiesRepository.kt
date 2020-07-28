package com.example.shiftstream2.feature.city.list.domain

import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType

interface CitiesRepository {

    fun getCities() : List<ItemType>
}