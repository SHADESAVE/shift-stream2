package com.example.shiftstream2.feature.city.list.presentation.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.feature.city.domain.entity.Forecast
import kotlinx.android.synthetic.main.rv_city_item.view.*

class CityViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val tittle = itemView.item_tittle
    private val desc = itemView.item_desc

    fun bind(city: Forecast) {
        tittle.text = city.name
        desc.text = "Температура в городе: ${city.temperature}"
    }

}