package com.example.shiftstream2.itemtypes

import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.viewholders.CityViewHolder

data class CityItem(
    val name: String,
    val temperature: Double
) : ItemType {

    override fun getItemViewType(): Int =
        ItemType.LIST

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as CityViewHolder).bind(this)
    }
}