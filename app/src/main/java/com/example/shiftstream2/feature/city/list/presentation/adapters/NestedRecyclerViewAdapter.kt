package com.example.shiftstream2.feature.city.list.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.R
import com.example.shiftstream2.feature.utils.adapter.diffNotifyChanges
import com.example.shiftstream2.feature.city.domain.entity.NestedItem
import com.example.shiftstream2.feature.city.list.presentation.adapters.viewholders.NestedViewHolder


class NestedRecyclerViewAdapter(
    private val nestedClickListener: (NestedItem) -> Unit
) : RecyclerView.Adapter<NestedViewHolder>() {

    private val list: MutableList<NestedItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedViewHolder {
        val holder = NestedViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.rv_nested_rv_item_item, parent, false)
        )
        holder.itemView.setOnClickListener {
            nestedClickListener(list[holder.adapterPosition])
        }
        return holder
    }

    fun setList(newList: List<NestedItem>) {
        val diff = diffNotifyChanges(
            list,
            newList
        ) { old, new ->
            old.nestedId == new.nestedId
        }
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int =
        list.size


    override fun onBindViewHolder(holder: NestedViewHolder, position: Int) {
        holder.bind(list[position])
    }

}