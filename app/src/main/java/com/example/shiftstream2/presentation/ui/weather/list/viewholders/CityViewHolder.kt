package com.example.shiftstream2.presentation.ui.weather.list.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.model.entity.CityItem
import kotlinx.android.synthetic.main.rv_city_item.view.*

class CityViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val tittle = itemView.item_tittle
    private val desc = itemView.item_desc

    fun bind(city: CityItem) {
        tittle.text = city.name
        desc.text = "Температура в городе: ${city.temperature}"
    }

}