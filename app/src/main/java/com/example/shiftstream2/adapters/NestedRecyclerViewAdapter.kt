package com.example.shiftstream2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.R
import com.example.shiftstream2.itemtypes.ItemType
import com.example.shiftstream2.itemtypes.nestedrecyclerview.NestedItem
import com.example.shiftstream2.viewholders.ItemClickListener
import com.example.shiftstream2.viewholders.NestedViewHolder


class NestedRecyclerViewAdapter :
    RecyclerView.Adapter<NestedViewHolder>() {

    private val list: MutableList<NestedItem> = mutableListOf()
    private var nestedAdapterListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedViewHolder =
        NestedViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.rv_nested_rv_item_item, parent, false), nestedAdapterListener
        )

    fun setList(newList: List<NestedItem>) {

        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun setNestedListener(listener: ItemClickListener) {
        nestedAdapterListener = listener
    }

    override fun getItemCount(): Int =
        list.size


    override fun onBindViewHolder(holder: NestedViewHolder, position: Int) {
        holder.bind(list[position])
    }

}