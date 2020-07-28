package com.example.shiftstream2.feature.city.list.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shiftstream2.feature.city.list.data.CityRepositoryImpl
import com.example.shiftstream2.feature.city.list.data.NetworkCityDataSourceImpl
import com.example.shiftstream2.feature.city.list.domain.GetCitiesUseCase
import com.example.shiftstream2.feature.city.list.presentation.CitiesListViewModel

class CitiesListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass == CitiesListViewModel::class.java) {
            val networkDataSource = NetworkCityDataSourceImpl()
            val cityRepository = CityRepositoryImpl(networkDataSource)
            val getCitiesUseCase = GetCitiesUseCase(cityRepository)

            return CitiesListViewModel(getCitiesUseCase) as T
        } else {
            error("Unexpected class $modelClass")
        }
    }
}