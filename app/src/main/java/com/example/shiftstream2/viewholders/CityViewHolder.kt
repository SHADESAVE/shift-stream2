package com.example.shiftstream2.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.itemtypes.CityItem
import kotlinx.android.synthetic.main.rv_city_item.view.*

class CityViewHolder(
    itemView: View,
    private val cityClickListener: ItemClickListener?
) : RecyclerView.ViewHolder(itemView) {

    private val tittle = itemView.item_tittle
    private val desc = itemView.item_desc

    fun bind(city: CityItem) {
        itemView.setOnClickListener {
            cityClickListener?.onItemClick(city)
        }
        tittle.text = city.name
        desc.text = "Температура в городе: ${city.temperature}"
    }

}