package com.example.shiftstream2.feature.city.list.domain

import com.example.common.CreateCityDto

class AddForecastUseCase(
    private val citiesRepository: CitiesRepository
) {

    suspend operator fun invoke(createCityDto: com.example.common.CreateCityDto) = citiesRepository.addForecast(createCityDto)
}