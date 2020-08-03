package com.example.server.db.table

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object TESTS : Table() {
    val id: Column<Long> = TESTS.long("id").autoIncrement().primaryKey()
    val name: Column<String> = TESTS.text("name")
    val temperature: Column<Double> = TESTS.double("temperature")
}
