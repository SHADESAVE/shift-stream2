package com.example.shiftstream2.viewholders

import com.example.shiftstream2.itemtypes.ItemType
import com.example.shiftstream2.itemtypes.nestedrecyclerview.NestedItem

interface ItemClickListener {
    fun onItemClick(item: ItemType)
    fun onNestedItemClick(nestedItem: NestedItem)
}
