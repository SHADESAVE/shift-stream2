package com.example.shiftstream2.feature.city.list.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import kotlinx.coroutines.CoroutineScope

class CityDataSourceFactory(
    private val repository: CitiesRepository,
    private val coroutineScope: CoroutineScope
) : DataSource.Factory<Long, ItemType>() {

    private val liveDataSource = MutableLiveData<CityItemKeyedDataSource>()

    override fun create(): DataSource<Long, ItemType> {
        val source =
            CityItemKeyedDataSource(
                repository,
                coroutineScope
            )
        liveDataSource.postValue(source)
        return source
    }
}