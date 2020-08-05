package com.example.shiftstream2.feature.city.list.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shiftstream2.feature.city.domain.entity.Forecast
import com.example.shiftstream2.feature.city.domain.entity.NestedItem
import com.example.shiftstream2.feature.city.list.presentation.adapters.Header
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import com.example.shiftstream2.feature.city.list.presentation.adapters.NestedRecycler

class CitiesDiffUtilCallback: DiffUtil.ItemCallback<ItemType>() {
    override fun areItemsTheSame(oldItem: ItemType, newItem: ItemType): Boolean {
        if (oldItem is Forecast && newItem is Forecast)
            return oldItem.id == newItem.id
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ItemType, newItem: ItemType): Boolean {
       return when {
           oldItem is Forecast && newItem is Forecast -> {
               oldItem.name == newItem.name && oldItem.temperature == newItem.temperature
           }
           oldItem is NestedItem && newItem is NestedItem -> {
                oldItem.tittle == newItem.tittle && oldItem.imgLink == newItem.imgLink
           }
           oldItem is Header && newItem is Header -> {
               oldItem.header == newItem.header
           }
           oldItem is NestedRecycler && newItem is NestedRecycler -> {
               oldItem.list == newItem.list
           }
           else -> {
               throw error("Unexpected itemType: oldItem: $oldItem, newItem: $newItem")
           }
       }
    }
}