package com.example.shiftstream2.presentation.ui.weather.list.viewholders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shiftstream2.presentation.ui.weather.list.adapters.NestedRecyclerViewAdapter
import com.example.shiftstream2.model.entity.NestedItem
import kotlinx.android.synthetic.main.rv_nested_rv_item.view.*

class RecyclerViewHolder(
    itemView: View,
    nestedClickListener: (Any) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val adapter = NestedRecyclerViewAdapter(nestedClickListener)
    val nestedRecycler: RecyclerView = itemView.nested_rv
    val layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

    fun bind(nestedList: List<NestedItem>) {

        adapter.setList(nestedList)

        //Продолжение оптимизации прокрутки, во вложенном ресайклере подготавливаем 4 вьюшки

        layoutManager.initialPrefetchItemCount = 4

        nestedRecycler.layoutManager = layoutManager
        nestedRecycler.adapter = adapter
        nestedRecycler.setHasFixedSize(true)
    }
}