package com.example.shiftstream2.feature.city.list.domain

import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType

interface CitiesRepository {

    suspend fun getCities() : List<ItemType>
}