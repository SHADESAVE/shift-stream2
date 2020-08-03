package com.example.shiftstream2.feature.city.list.presentation.adapters

import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shiftstream2.R
import com.example.shiftstream2.feature.city.domain.entity.Forecast
import com.example.shiftstream2.feature.utils.adapter.diffNotifyChanges
import com.example.shiftstream2.feature.city.list.presentation.adapters.viewholders.CityViewHolder
import com.example.shiftstream2.feature.city.list.presentation.adapters.viewholders.HeaderViewHolder
import com.example.shiftstream2.feature.city.list.presentation.adapters.viewholders.RecyclerViewHolder
import kotlinx.android.synthetic.main.rv_city_item.view.*


class RecyclerViewAdapter(
    private val clickListener: (ItemType) -> Unit,
    private val forecastDeleteClickListener: (Forecast) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    private val list: MutableList<ItemType> = mutableListOf()
    private val positionList = SparseIntArray()
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun getItemViewType(position: Int): Int =
        list[position].getItemViewType()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = setViewHolder(parent, viewType)

        if (holder is CityViewHolder) {
            holder.itemView.setOnClickListener {
                clickListener(list[holder.adapterPosition])
            }
            holder.itemView.item_delete_button.setOnClickListener {
                forecastDeleteClickListener(list[holder.adapterPosition] as Forecast)
            }
        }

        //Оптимизация прокрутки, установка одного view-пула для внешнего и вложенного ресайклеров

        if (holder !is RecyclerViewHolder) return holder
        holder.nestedRecycler.setRecycledViewPool(viewPool)
        return holder
    }


    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].onBindViewHolder(holder, position)

        // Пытаемся вернуть позицию вложенного ресайклера

        if (holder !is RecyclerViewHolder) return
        val lastSeenFirstPosition: Int = positionList.get(position, 0)
        if (lastSeenFirstPosition >= 0) {
            holder.layoutManager.scrollToPositionWithOffset(lastSeenFirstPosition, 0)
        }
    }

    override fun onViewRecycled(viewHolder: ViewHolder) {

        // Сохраняем позицию вложенного ресайклера

        if (viewHolder !is RecyclerViewHolder) return
        val position = viewHolder.adapterPosition
        val firstVisiblePosition: Int = viewHolder.layoutManager.findFirstVisibleItemPosition()
        positionList.put(position, firstVisiblePosition)

        super.onViewRecycled(viewHolder)
    }

    fun setList(newList: List<ItemType>) {

        val diff = diffNotifyChanges(
            list,
            newList
        ) { old, new ->
            old == new
        }
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    private fun setViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =

        when (viewType) {
            ItemType.HEADER -> {
                HeaderViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.rv_header_item, parent, false)
                )
            }
            ItemType.NESTED_HORIZONTAL_RECYCLERVIEW -> {
                RecyclerViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.rv_nested_rv_item, parent, false), clickListener
                )
            }
            ItemType.LIST -> {
                CityViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.rv_city_item, parent, false)
                )
            }
            else -> {
                throw error("Неизвестный тип айтема в ресайклере: $viewType")
            }
        }
}