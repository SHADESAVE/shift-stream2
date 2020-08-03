package com.example.shiftstream2.feature.utils

import com.example.common.WeatherCity
import com.example.shiftstream2.feature.city.domain.entity.Forecast

fun WeatherCity.toCity() = Forecast(
    id = this.id,
    name = this.name,
    temperature = this.temperature
)