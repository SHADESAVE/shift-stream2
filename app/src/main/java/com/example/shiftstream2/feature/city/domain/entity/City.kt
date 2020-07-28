package com.example.shiftstream2.feature.city.domain.entity

import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import com.example.shiftstream2.feature.city.list.presentation.adapters.viewholders.CityViewHolder
import java.io.Serializable

data class City(
    val name: String,
    val temperature: Double
) : ItemType, Serializable {

    override fun getItemViewType(): Int =
        ItemType.LIST

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as CityViewHolder).bind(this)
    }
}