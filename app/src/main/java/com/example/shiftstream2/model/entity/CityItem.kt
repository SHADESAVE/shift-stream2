package com.example.shiftstream2.model.entity

import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.presentation.ui.weather.list.ItemType
import com.example.shiftstream2.presentation.ui.weather.list.viewholders.CityViewHolder
import java.io.Serializable

data class CityItem(
    val name: String,
    val temperature: Double
) : ItemType, Serializable {

    override fun getItemViewType(): Int =
        ItemType.LIST

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as CityViewHolder).bind(this)
    }
}