package com.example.shiftstream2.feature.city.list.presentation

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shiftstream2.R
import com.example.shiftstream2.feature.city.detail.presentation.CityDetailFragment
import com.example.shiftstream2.feature.city.domain.entity.City
import com.example.shiftstream2.feature.city.domain.entity.NestedItem
import com.example.shiftstream2.feature.city.list.di.CitiesListViewModelFactory
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import com.example.shiftstream2.feature.city.list.presentation.adapters.RecyclerViewAdapter
import com.example.shiftstream2.feature.city.thirdscreen.presentation.ThirdFragment
import com.example.shiftstream2.feature.utils.progress.Status
import kotlinx.android.synthetic.main.create_weather_forecast_dialog.*
import kotlinx.android.synthetic.main.fragment_main.*

class CitiesListFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: CitiesListViewModel by viewModels {
            CitiesListViewModelFactory()
        }
        val adapter = RecyclerViewAdapter {
            viewModel.itemClicked(it)
        }

        lateinit var forecastCreateDialog: AlertDialog
        forecastCreateDialog = AlertDialog
            .Builder(view.context)
            .setView(R.layout.create_weather_forecast_dialog)
            .setPositiveButton("Создать") { _: DialogInterface, _: Int ->
                viewModel.createForecast(
                    forecastCreateDialog.dialog_notification_name.text.toString(),
                    forecastCreateDialog.dialog_notification_desc.text.toString()
                )
            }
            .setNegativeButton("Отмена") { _: DialogInterface, _: Int -> }
            .create()

        create_forecast_fab.setOnClickListener {
            viewModel.fabClicked(it)
        }

        viewModel.itemClickEvent.observe(viewLifecycleOwner, Observer(::itemClicked))
        viewModel.progressStatus.observe(viewLifecycleOwner, Observer(::statusChanged))
        viewModel.items.observe(viewLifecycleOwner, Observer {
            setItemList(it, adapter)
        })
        viewModel.fabClickEvent.observe(viewLifecycleOwner, Observer {
            fabClicked(forecastCreateDialog)
        })

        main_rv.layoutManager = LinearLayoutManager(view.context)
        main_rv.adapter = adapter
        main_rv.setHasFixedSize(true)
    }

    private fun setItemList(list: List<ItemType>, adapter: RecyclerViewAdapter) {
        adapter.setList(list)
    }

    private fun statusChanged(status: Int) {
        when (status) {
            Status.LOADING -> {
                create_forecast_fab.hide()
                error_text.visibility = View.GONE
                progress_bar_layout.visibility = View.VISIBLE
            }
            Status.DONE -> {
                error_text.visibility = View.GONE
                progress_bar_layout.visibility = View.GONE
                create_forecast_fab.show()
            }
            Status.ERROR -> {
                create_forecast_fab.hide()
                progress_bar_layout.visibility = View.GONE
                error_text.visibility = View.VISIBLE
            }
        }
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

    private fun fabClicked(dialog: AlertDialog) {
        dialog.show()
    }

}