package com.example.shiftstream2.model.entity

import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.presentation.ui.weather.list.ItemType
import com.example.shiftstream2.presentation.ui.weather.list.viewholders.HeaderViewHolder

data class HeaderItem(
    val header: String
) : ItemType {
    override fun getItemViewType(): Int =
        ItemType.HEADER

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as HeaderViewHolder).bind(this)
    }
}