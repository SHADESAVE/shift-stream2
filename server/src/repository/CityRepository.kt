package com.example.server.repository

import com.example.common.CreateCityDto
import com.example.common.WeatherCity
import com.example.server.db.dbQuery
import com.example.server.db.table.TESTS
import com.example.server.db.table.toWeatherCity
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class CityRepository {

    suspend fun getAll() = dbQuery {
        TESTS.selectAll().map { it.toWeatherCity() }
    }

    suspend fun add(createCityDto: CreateCityDto) = dbQuery {
        TESTS.insert { insertStatement ->
            insertStatement[name] = createCityDto.name
            insertStatement[temperature] = createCityDto.temperature
        }
    }

    suspend fun delete(id: Long) = dbQuery {
        TESTS.deleteWhere {
            TESTS.id.eq(id)
        }
    }

    suspend fun update(weatherCity: WeatherCity) = dbQuery {
        TESTS.update({ TESTS.id eq weatherCity.id }) {
            it[name] = weatherCity.name
            it[temperature] = weatherCity.temperature
        }
    }
}