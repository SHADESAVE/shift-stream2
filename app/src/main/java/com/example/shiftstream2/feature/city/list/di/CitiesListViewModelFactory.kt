package com.example.shiftstream2.feature.city.list.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shiftstream2.feature.city.list.data.CitiesApi
import com.example.shiftstream2.feature.city.list.data.CityRepositoryImpl
import com.example.shiftstream2.feature.city.list.data.NetworkCityDataSourceImpl
import com.example.shiftstream2.feature.city.list.domain.AddForecastUseCase
import com.example.shiftstream2.feature.city.list.domain.DeleteForecastUseCase
import com.example.shiftstream2.feature.city.list.domain.GetCitiesUseCase
import com.example.shiftstream2.feature.city.list.presentation.CitiesListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CitiesListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass == CitiesListViewModel::class.java) {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://shift-stream-backend.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            val api = retrofit.create(CitiesApi::class.java)

            val networkDataSource = NetworkCityDataSourceImpl(api)
            val cityRepository = CityRepositoryImpl(networkDataSource)

            val getCitiesUseCase = GetCitiesUseCase(cityRepository)
            val addForecastUseCase = AddForecastUseCase(cityRepository)
            val deleteForecastUseCase = DeleteForecastUseCase(cityRepository)

            return CitiesListViewModel(getCitiesUseCase, addForecastUseCase, deleteForecastUseCase) as T
        } else {
            error("Unexpected class $modelClass")
        }
    }
}