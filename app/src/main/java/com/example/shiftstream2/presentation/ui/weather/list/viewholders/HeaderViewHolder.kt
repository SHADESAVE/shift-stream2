package com.example.shiftstream2.presentation.ui.weather.list.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.model.entity.HeaderItem
import kotlinx.android.synthetic.main.rv_header_item.view.*

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val header = itemView.header

    fun bind(headerItem: HeaderItem) {
        header.text = headerItem.header
    }
}