package com.example.shiftstream2.presentation.ui.weather.list.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.model.entity.NestedItem
import kotlinx.android.synthetic.main.rv_nested_rv_item_item.view.*


class NestedViewHolder(
    itemView: View
): RecyclerView.ViewHolder(itemView) {

    //    private val img = itemView.nested_image
    private val tittle = itemView.nested_tittle

    fun bind(nestedItem: NestedItem) {

        tittle.text = nestedItem.tittle
//        img.setImageFromURL(nestedItem.imgLink)
    }
}