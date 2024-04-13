package com.narify.weathernowandlater.domain.weather.repository

import com.narify.weathernowandlater.domain.weather.model.Weather

interface WeatherRepository {
    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Weather

    suspend fun getDailyForecast(
        latitude: Double,
        longitude: Double
    ): List<Weather>
}
