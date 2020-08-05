package com.example.shiftstream2.feature.city.list.domain

import androidx.paging.ItemKeyedDataSource
import com.example.shiftstream2.feature.city.domain.entity.Forecast
import com.example.shiftstream2.feature.city.list.presentation.adapters.ItemType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CityItemKeyedDataSource(
    private val repository: CitiesRepository,
    private val coroutineScope: CoroutineScope
) : ItemKeyedDataSource<Long, ItemType>() {

    private var key: Long = 0

    override fun getKey(item: ItemType): Long {
        if (item is Forecast)
            key = item.id
        return key
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<ItemType>) {
        coroutineScope.launch {
            val start = params.key
            val size = params.requestedLoadSize
            val cities = repository.getPage(start, size)
            callback.onResult(cities)
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<ItemType>) {
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<ItemType>) {
        coroutineScope.launch {
            val size = params.requestedLoadSize / 3
            val cities = repository.getPage(0, size)
            callback.onResult(cities)
        }
    }

}