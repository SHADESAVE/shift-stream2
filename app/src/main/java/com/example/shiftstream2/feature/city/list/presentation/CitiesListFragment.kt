package com.example.shiftstream2.feature.city.list.presentation

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shiftstream2.R
import com.example.shiftstream2.feature.city.detail.presentation.CityDetailFragment
import com.example.shiftstream2.feature.city.domain.entity.Forecast
import com.example.shiftstream2.feature.city.domain.entity.NestedItem
import com.example.shiftstream2.feature.city.list.di.CitiesListViewModelFactory
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import com.example.shiftstream2.feature.city.list.presentation.adapters.RecyclerViewAdapter
import com.example.shiftstream2.feature.city.thirdscreen.presentation.ThirdFragment
import com.example.shiftstream2.feature.utils.fragment.replaceFragment
import com.example.shiftstream2.feature.utils.progress.Status
import kotlinx.android.synthetic.main.create_weather_forecast_dialog.*
import kotlinx.android.synthetic.main.fragment_main.*

class CitiesListFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: CitiesListViewModel by viewModels {
            CitiesListViewModelFactory()
        }

        val adapter = RecyclerViewAdapter(
            CitiesDiffUtilCallback(),
            { itemType -> viewModel.itemClicked(itemType) },
            { forecast -> viewModel.delButtonClicked(forecast) }
        )

        val dialog = setupCreateForecastDialog(viewModel)

        viewModel.itemClickEvent.observe(viewLifecycleOwner, Observer(::itemClicked))
        viewModel.progressStatus.observe(viewLifecycleOwner, Observer(::statusChanged))

        viewModel.fabClickEvent.observe(viewLifecycleOwner, Observer {
            fabClicked(dialog)
        })
        viewModel.emptyNameEvent.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "Пустое поле город", Toast.LENGTH_SHORT).show()
        })
        viewModel.emptyTemperatureEvent.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "Пустое поле температура", Toast.LENGTH_SHORT).show()
        })

        main_rv.layoutManager = LinearLayoutManager(view.context)
        main_rv.setHasFixedSize(true)

        viewModel.items.observe(viewLifecycleOwner, Observer {
            setItemList(it, adapter)
        })
    }

    private fun setupCreateForecastDialog(viewModel: CitiesListViewModel): AlertDialog {
        lateinit var forecastCreateDialog: AlertDialog
        forecastCreateDialog =
            AlertDialog
                .Builder(view?.context)
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
        return forecastCreateDialog
    }

    private fun setItemList(list: PagedList<ItemType>, adapter: RecyclerViewAdapter) {
        adapter.submitList(list)
        main_rv.adapter = adapter
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

    private fun itemClicked(itemType: ItemType) {
        when (itemType) {

            is Forecast -> {
                activity?.let { replaceFragment(it, CityDetailFragment(), itemType) }
            }

            is NestedItem -> {
                activity?.let { replaceFragment(it, ThirdFragment(), itemType) }
            }

            else -> {
                throw error("Нажат неизвестный элемент: $itemType")
            }
        }
    }

    private fun fabClicked(dialog: AlertDialog) {
        dialog.show()
    }

    private fun toast(text: String) {

    }
}