package com.example.shiftstream2.feature.city.list.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shiftstream2.R
import com.example.shiftstream2.feature.city.domain.entity.City
import com.example.shiftstream2.feature.city.list.presentation.adapters.RecyclerViewAdapter
import com.example.shiftstream2.feature.city.domain.entity.NestedItem
import com.example.shiftstream2.feature.city.detail.presentation.CityDetailFragment
import com.example.shiftstream2.feature.city.list.di.CitiesListViewModelFactory
import com.example.shiftstream2.feature.city.thirdscreen.presentation.ThirdFragment
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_main.view.main_rv

class CitiesListFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: CitiesListViewModel by viewModels {
            CitiesListViewModelFactory()
        }
        val adapter = RecyclerViewAdapter {
            viewModel.itemClicked(it)
        }

        viewModel.itemClickEvent.observe(viewLifecycleOwner, Observer(::itemClicked))
        viewModel.items.observe(viewLifecycleOwner, Observer {
            setItemList(it, adapter)
        })

        main_rv.layoutManager = LinearLayoutManager(view.context)
        main_rv.adapter = adapter
        main_rv.setHasFixedSize(true)
    }

    private fun setItemList(list: List<ItemType>, adapter: RecyclerViewAdapter) {
        adapter.setList(list)
    }

    private fun itemClicked(itemType: Any) {
        when (itemType) {

            is City -> {
                activity?.supportFragmentManager?.beginTransaction()?.replace(
                    R.id.fragment_container,
                    CityDetailFragment()
                        .also {
                            it.param1 = itemType
                        }
                )?.addToBackStack(null)?.commit()
            }

            is NestedItem -> {
                activity?.supportFragmentManager?.beginTransaction()?.replace(
                    R.id.fragment_container,
                    ThirdFragment()
                        .also {
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