package com.example.shiftstream2.feature.city.list.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shiftstream2.feature.city.domain.entity.City
import com.example.shiftstream2.feature.city.list.domain.entity.Header
import com.example.shiftstream2.feature.city.domain.entity.NestedItem
import com.example.shiftstream2.feature.city.list.domain.GetCitiesUseCase
import com.example.shiftstream2.feature.city.list.domain.entity.NestedRecycler
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import com.example.shiftstream2.feature.utils.viewmodel.SingleLiveEvent
import java.math.RoundingMode
import kotlin.random.Random

class CitiesListViewModel(
    getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    val items = MutableLiveData<List<ItemType>>()
    val itemClickEvent = SingleLiveEvent<Any>()

    init {
        items.value = getCitiesUseCase()
    }

    fun itemClicked(itemType: Any) {
        itemClickEvent(itemType)
    }

}