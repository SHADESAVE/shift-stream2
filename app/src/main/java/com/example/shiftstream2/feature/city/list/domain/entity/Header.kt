package com.example.shiftstream2.feature.city.list.domain.entity

import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import com.example.shiftstream2.feature.city.list.presentation.adapters.viewholders.HeaderViewHolder

data class Header(
    val header: String
) : ItemType {
    override fun getItemViewType(): Int =
        ItemType.HEADER

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as HeaderViewHolder).bind(this)
    }
}