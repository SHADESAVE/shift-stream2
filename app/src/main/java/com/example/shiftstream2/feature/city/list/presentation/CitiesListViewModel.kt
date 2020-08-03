package com.example.shiftstream2.feature.city.list.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shiftstream2.feature.city.domain.entity.City
import com.example.shiftstream2.feature.city.list.domain.entity.Header
import com.example.shiftstream2.feature.city.domain.entity.NestedItem
import com.example.shiftstream2.feature.city.list.domain.GetCitiesUseCase
import com.example.shiftstream2.feature.city.list.domain.entity.NestedRecycler
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import com.example.shiftstream2.feature.utils.progress.Status
import com.example.shiftstream2.feature.utils.viewmodel.SingleLiveEvent
import kotlinx.coroutines.launch
import java.math.RoundingMode
import kotlin.random.Random

class CitiesListViewModel(
    getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    val items = MutableLiveData<List<ItemType>>()
    val itemClickEvent = SingleLiveEvent<Any>()
    var progressStatus = SingleLiveEvent<Int>()


        init {
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

    fun itemClicked(itemType: Any) {
        itemClickEvent(itemType)
    }

}