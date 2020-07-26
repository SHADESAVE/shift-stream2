package com.example.shiftstream2.presentation.ui.weather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shiftstream2.R
import com.example.shiftstream2.anyArgument
import com.example.shiftstream2.model.entity.CityItem
import com.example.shiftstream2.model.entity.HeaderItem
import com.example.shiftstream2.presentation.ui.weather.list.adapters.RecyclerViewAdapter
import com.example.shiftstream2.model.entity.NestedItem
import com.example.shiftstream2.model.entity.NestedRecycler
import com.example.shiftstream2.presentation.ui.SecondFragment
import com.example.shiftstream2.presentation.ui.ThirdFragment
import com.example.shiftstream2.presentation.ui.weather.list.ItemType
import com.example.shiftstream2.presentation.viewmodel.weather.list.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.math.RoundingMode
import kotlin.random.Random

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = WeatherViewModel()
        val recyclerView = view.main_rv

        val adapter = RecyclerViewAdapter {
            viewModel.itemClicked(it)
        }

        viewModel.items.observe(viewLifecycleOwner, Observer {
            setItemList(it, adapter)
        })
        viewModel.itemClickEvent.observe(viewLifecycleOwner, Observer  (::itemClicked))

        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

    }

    private fun setItemList(list: List<ItemType>, adapter: RecyclerViewAdapter) {
        adapter.setList(list)
    }

    private fun itemClicked(itemType: Any) {
        when (itemType) {
            is CityItem -> {
                activity?.supportFragmentManager?.beginTransaction()?.replace(
                    R.id.fragment_container,
                    SecondFragment().also {
                        it.param1 = itemType
                    }
                )?.addToBackStack(null)?.commit()
            }
            is NestedItem -> {
                activity?.supportFragmentManager?.beginTransaction()?.replace(
                    R.id.fragment_container,
                    ThirdFragment().also {
                        it.param1 = itemType
                    }
                )?.addToBackStack(null)?.commit()
            }
            else -> {
                throw error("Нажат неизвестный элемент: $itemType")
            }
        }
    }

}