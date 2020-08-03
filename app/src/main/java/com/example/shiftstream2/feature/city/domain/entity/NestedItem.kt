package com.example.shiftstream2.feature.city.domain.entity

import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import com.example.shiftstream2.feature.city.list.presentation.adapters.viewholders.NestedViewHolder
import java.io.Serializable

data class NestedItem(
    val nestedId: Long,
    val imgLink: String? = null,
    val tittle: String
) : Serializable, ItemType {
    override fun getItemViewType(): Int =
        ItemType.NESTED_ITEM

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as NestedViewHolder).bind(this)
    }
}