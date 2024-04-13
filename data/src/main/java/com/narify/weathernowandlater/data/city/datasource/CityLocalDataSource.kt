package com.narify.weathernowandlater.data.city.datasource

import com.narify.weathernowandlater.domain.city.model.City
import kotlinx.coroutines.flow.Flow

interface CityLocalDataSource {
    suspend fun saveCity(city: City)
    fun getSavedCity(): Flow<City>
}
