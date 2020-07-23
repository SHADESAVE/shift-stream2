package com.example.shiftstream2.adapters

import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shiftstream2.R
import com.example.shiftstream2.itemtypes.ItemType
import com.example.shiftstream2.viewholders.CityViewHolder
import com.example.shiftstream2.viewholders.HeaderViewHolder
import com.example.shiftstream2.viewholders.ItemClickListener
import com.example.shiftstream2.viewholders.OuterViewHolder


class MyRecyclerViewAdapter :
    RecyclerView.Adapter<ViewHolder>() {

    private val list: MutableList<ItemType> = mutableListOf()
    private var adapterListener: ItemClickListener? = null
    private val positionList = SparseIntArray()

    override fun getItemViewType(position: Int): Int =
        list[position].getItemViewType()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        setViewHolder(parent, viewType)

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].onBindViewHolder(holder, position)

        // Пытаемся вернуть позицию вложенного ресайклера
        try {
            holder as OuterViewHolder
            val lastSeenFirstPosition: Int = positionList.get(position, 0)
            if (lastSeenFirstPosition >= 0) {
                holder.layoutManager.scrollToPositionWithOffset(lastSeenFirstPosition, 0)
            }
        } catch (t: Throwable) {
        }
    }

    override fun onViewRecycled(viewHolder: ViewHolder) {

        // Cохраняем позицию вложенного ресайклера
        try {
            viewHolder as OuterViewHolder
            val position = viewHolder.adapterPosition
            val firstVisiblePosition: Int = viewHolder.layoutManager.findFirstVisibleItemPosition()
            positionList.put(position, firstVisiblePosition)
        } catch (t: Throwable) {
        }

        super.onViewRecycled(viewHolder)
    }

    fun setList(newList: List<ItemType>) {

        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun setListener(listener: ItemClickListener) {
        adapterListener = listener
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
                OuterViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.rv_nested_rv_item, parent, false), adapterListener
                )
            }
            ItemType.LIST -> {
                CityViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.rv_city_item, parent, false), adapterListener)
            }
            else -> {
                throw error("Неизвестный тип айтема в ресайклере")
            }
        }
}