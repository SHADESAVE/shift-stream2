package com.example.shiftstream2.feature.city.list.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.feature.city.domain.entity.NestedItem
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import com.example.shiftstream2.feature.city.list.presentation.adapters.viewholders.RecyclerViewHolder

data class NestedRecycler(
    val list: List<NestedItem>
) : ItemType {

    override fun getItemViewType(): Int =
        ItemType.NESTED_HORIZONTAL_RECYCLERVIEW

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as RecyclerViewHolder).bind(list)
    }
}

