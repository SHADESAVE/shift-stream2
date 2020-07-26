package com.example.shiftstream2.model.entity

import java.io.Serializable

data class NestedItem(
    val nestedId: Long,
    val imgLink: String? = null,
    val tittle: String
) : Serializable