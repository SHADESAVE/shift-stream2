package com.example.shiftstream2.model.entity

import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.presentation.ui.weather.list.ItemType
import com.example.shiftstream2.presentation.ui.weather.list.viewholders.RecyclerViewHolder

data class NestedRecycler(
    val list: List<NestedItem>
) : ItemType {

    override fun getItemViewType(): Int =
        ItemType.NESTED_HORIZONTAL_RECYCLERVIEW

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as RecyclerViewHolder).bind(list)
    }
}

