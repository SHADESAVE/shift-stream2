package com.example.shiftstream2.itemtypes.nestedrecyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.itemtypes.ItemType
import com.example.shiftstream2.viewholders.OuterViewHolder

class NestedRecyclerItem(
    val list: List<NestedItem>
) : ItemType {

    override fun getItemViewType(): Int =
        ItemType.NESTED_HORIZONTAL_RECYCLERVIEW

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as OuterViewHolder).bind(list)
    }
}