package com.example.shiftstream2.itemtypes

import androidx.recyclerview.widget.RecyclerView

interface ItemType {

    fun getItemViewType(): Int
    fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int)

    companion object {
        const val HEADER = 0
        const val NESTED_HORIZONTAL_RECYCLERVIEW = 1
        const val LIST = 2
    }
}