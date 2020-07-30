package com.example.server.db.table

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Cities : Table() {
    val id: Column<Long> = Cities.long("id").autoIncrement().primaryKey()
    val name: Column<String> = Cities.text("name")
    val temperature: Column<Double> = Cities.double("temperature")
}
