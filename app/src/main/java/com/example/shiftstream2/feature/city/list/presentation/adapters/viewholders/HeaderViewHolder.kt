package com.example.shiftstream2.feature.city.list.presentation.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.feature.city.list.domain.entity.Header
import kotlinx.android.synthetic.main.rv_header_item.view.*

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val header = itemView.header

    fun bind(header: Header) {
        this.header.text = header.header
    }
}