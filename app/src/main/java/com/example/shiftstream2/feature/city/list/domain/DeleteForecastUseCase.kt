package com.example.shiftstream2.feature.city.list.domain

import com.example.common.CreateCityDto

class DeleteForecastUseCase(
    private val citiesRepository: CitiesRepository
) {

    suspend operator fun invoke(id: Long) = citiesRepository.deleteForecast(id)
}