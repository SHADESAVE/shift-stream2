package com.example.shiftstream2.feature.city.list.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.CreateCityDto
import com.example.shiftstream2.feature.city.domain.entity.Forecast
import com.example.shiftstream2.feature.city.list.domain.AddForecastUseCase
import com.example.shiftstream2.feature.city.list.domain.DeleteForecastUseCase
import com.example.shiftstream2.feature.city.list.domain.GetCitiesUseCase
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import com.example.shiftstream2.feature.utils.progress.Status
import com.example.shiftstream2.feature.utils.viewmodel.SingleLiveEvent
import kotlinx.coroutines.launch

class CitiesListViewModel(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val addForecastUseCase: AddForecastUseCase,
    private val deleteForecastUseCase: DeleteForecastUseCase
) : ViewModel() {

    val items = MutableLiveData<List<ItemType>>()
    val itemClickEvent = SingleLiveEvent<ItemType>()
    val fabClickEvent = SingleLiveEvent<View>()

    var progressStatus = SingleLiveEvent<Int>()

    init {
        updateList()
    }

    private fun updateList() {
        viewModelScope.launch {
            try {
                progressStatus.value = Status.LOADING
                items.value = getCitiesUseCase()
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

    fun delButtonClicked(forecast: Forecast) {
        viewModelScope.launch {
            try {
                progressStatus.value = Status.LOADING
                deleteForecastUseCase(forecast.id)
                updateList()
            } catch (e: Exception) {
                Log.d("deleteForecastError: ", e.toString())
                progressStatus.value = Status.ERROR
            }
        }
    }

    fun createForecast(city: String, temperature: String) {
        viewModelScope.launch {
            try {
                progressStatus.value = Status.LOADING
                addForecastUseCase(CreateCityDto(city, temperature.toDouble()))
                updateList()
            } catch (e: Exception) {
                Log.d("addForecastError: ", e.toString())
                progressStatus.value = Status.ERROR
            }
        }
    }
}