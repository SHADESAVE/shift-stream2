package com.example.shiftstream2.feature.city.domain.entity

import java.io.Serializable

data class NestedItem(
    val nestedId: Long,
    val imgLink: String? = null,
    val tittle: String
) : Serializable