package com.example.shiftstream2.viewholders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.adapters.NestedRecyclerViewAdapter
import com.example.shiftstream2.itemtypes.nestedrecyclerview.NestedItem
import kotlinx.android.synthetic.main.rv_nested_rv_item.view.*

class OuterViewHolder(
    itemView: View,
    private val adapterListener: ItemClickListener?
) : RecyclerView.ViewHolder(itemView) {

    private val nestedRecycler: RecyclerView = itemView.nested_rv
    private val adapter = NestedRecyclerViewAdapter()
    val layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

    fun bind(nestedList: List<NestedItem>) {
        adapter.setList(nestedList)
        adapterListener?.let { adapter.setNestedListener(it) }
        nestedRecycler.adapter = adapter
        nestedRecycler.layoutManager = layoutManager
        nestedRecycler.hasFixedSize()
    }
}