package com.example.shiftstream2.feature.city.list.presentation

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.common.CreateCityDto
import com.example.shiftstream2.feature.city.domain.entity.Forecast
import com.example.shiftstream2.feature.city.list.domain.*
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import com.example.shiftstream2.feature.city.list.presentation.adapters.RecyclerViewAdapter
import com.example.shiftstream2.feature.utils.progress.Status
import com.example.shiftstream2.feature.utils.viewmodel.SingleLiveEvent
import kotlinx.coroutines.launch

class CitiesListViewModel(
    private val cityRepository: CitiesRepository
) : ViewModel() {

    val itemClickEvent = SingleLiveEvent<ItemType>()
    val fabClickEvent = SingleLiveEvent<View>()
    val emptyNameEvent = SingleLiveEvent<Unit>()
    val emptyTemperatureEvent = SingleLiveEvent<Unit>()
    var progressStatus = SingleLiveEvent<Int>()

    private val factory = CityDataSourceFactory(cityRepository, viewModelScope)
    private val config = Config(
        pageSize = 10,
        prefetchDistance = 1,
        enablePlaceholders = false
    )

    val items: LiveData<PagedList<ItemType>> = LivePagedListBuilder(factory, config).build()

    private fun updateList() {
        viewModelScope.launch {
            try {
                progressStatus.value = Status.LOADING
                items.value?.dataSource?.invalidate()
                progressStatus.value = Status.DONE
            } catch (e: Exception) {
                Log.d("getCitiesError: ", e.toString())
                progressStatus.value = Status.ERROR
            }
        }
    }

    fun itemClicked(itemType: ItemType) {
        itemClickEvent(itemType)
    }

    fun fabClicked(view: View) {
        fabClickEvent(view)
    }

    fun delButtonClicked(
        forecast: Forecast
    ) {
        viewModelScope.launch {
            try {
                progressStatus.value = Status.LOADING
                cityRepository.deleteForecast(forecast.id)
                updateList()
            } catch (e: Exception) {
                Log.e("deleteForecastError: ", e.toString())
                progressStatus.value = Status.ERROR
            }
        }
    }

    fun createForecast(city: String, temperature: String) {
        when {
            city.isEmpty() -> emptyNameEvent(Unit)
            temperature.isEmpty() -> emptyTemperatureEvent(Unit)
            else ->
                viewModelScope.launch {
                    try {
                        progressStatus.value = Status.LOADING
                        cityRepository.addForecast(CreateCityDto(city, temperature.toDouble()))
                        updateList()
                    } catch (e: Exception) {
                        Log.e("addForecastError: ", e.toString())
                        progressStatus.value = Status.ERROR
                    }
                }
        }
    }
}