package com.example.shiftstream2.feature.city.list.domain

import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType

class GetCitiesUseCase(
    private val citiesRepository: CitiesRepository
) {

    suspend operator fun invoke(): List<ItemType> = citiesRepository.getCities()
}