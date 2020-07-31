package com.example.server.db.table

import com.example.common.WeatherCity
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toWeatherCity() = WeatherCity(
    id = this[Cities.id],
    name = this[Cities.name],
    temperature = this[Cities.temperature]
)