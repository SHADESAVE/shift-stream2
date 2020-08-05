package com.example.shiftstream2.feature.utils.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType


fun <T> diffNotifyChanges(
    oldList: List<T>,
    newList: List<T>,
    compare: (T, T) -> Boolean
): DiffUtil.DiffResult =
    DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            compare(oldList[oldItemPosition], newList[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
    })