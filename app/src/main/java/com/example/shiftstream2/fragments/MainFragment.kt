package com.example.shiftstream2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shiftstream2.R
import com.example.shiftstream2.adapters.MyRecyclerViewAdapter
import com.example.shiftstream2.itemtypes.*
import com.example.shiftstream2.itemtypes.nestedrecyclerview.NestedItem
import com.example.shiftstream2.itemtypes.nestedrecyclerview.NestedRecyclerItem
import com.example.shiftstream2.viewholders.ItemClickListener
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.math.RoundingMode
import kotlin.random.Random

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.main_rv
        val adapter = MyRecyclerViewAdapter()

        adapter.setList(getList())
        adapter.setListener(object : ItemClickListener {
            override fun onItemClick(item: ItemType) {
                itemClicked(item)
            }

            override fun onNestedItemClick(nestedItem: NestedItem) {
                itemClicked(nestedItem)
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
        recyclerView.hasFixedSize()

    }

    private fun getList(): List<ItemType> {

        val rnd = Random

        val itemList = mutableListOf<ItemType>()
        val nestedList = mutableListOf<NestedItem>()

        for (i in 1..25) {
            nestedList.add(
                NestedItem(
                    null,
                    "День $i"
                )
            )
        }

        itemList.add(HeaderItem("Погода"))

        itemList.add(
            CityItem(
                "Новосибирск",
                (rnd.nextDouble(50.0) - rnd.nextDouble(50.0)).toBigDecimal()
                    .setScale(1, RoundingMode.UP).toDouble()
            )
        )

        itemList.add(
            NestedRecyclerItem(
                nestedList
            )
        )

        itemList.add(
            CityItem(
                "Томск",
                (rnd.nextDouble(50.0) - rnd.nextDouble(50.0)).toBigDecimal()
                    .setScale(1, RoundingMode.UP).toDouble()
            )
        )
        itemList.add(
            NestedRecyclerItem(
                nestedList
            )
        )

        itemList.add(HeaderItem("Погода в других городах:"))

        for (i in 0..50) {
            itemList.add(
                CityItem(
                    "Город $i",
                    (rnd.nextDouble(50.0) - rnd.nextDouble(50.0)).toBigDecimal()
                        .setScale(1, RoundingMode.UP).toDouble()
                )
            )
        }

        return itemList
    }

    private fun itemClicked(itemType: Any) {
        when (itemType) {
            is CityItem -> {
                activity?.supportFragmentManager?.beginTransaction()?.replace(
                    R.id.fragment_container,
                    SecondFragment()
                )?.addToBackStack(null)?.commit()
            }
            is NestedItem -> {
                activity?.supportFragmentManager?.beginTransaction()?.replace(
                    R.id.fragment_container,
                    ThirdFragment()
                )?.addToBackStack(null)?.commit()
            }
            else -> {
                throw error("Нажат неизвестный элемент: $itemType")
            }
        }
    }

}