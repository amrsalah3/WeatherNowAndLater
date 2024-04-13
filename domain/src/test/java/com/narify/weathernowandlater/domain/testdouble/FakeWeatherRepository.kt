package com.narify.weathernowandlater.domain.testdouble

import com.narify.weathernowandlater.domain.weather.model.Weather
import com.narify.weathernowandlater.domain.weather.repository.WeatherRepository

internal class FakeWeatherRepository : WeatherRepository {

    private val currentWeather = Weather(
        day = "Saturday",
        temperature = 15.0,
        humidity = 60.0,
        windSpeed = 7.0,
        condition = "Clear",
        description = "clear sky",
        iconUrl = "https://openweathermap.org/img/wn/01d@2x.png"
    )

    private val dailyForecast = listOf(
        Weather(
            day = "Sunday",
            temperature = 12.0,
            humidity = 40.0,
            windSpeed = 10.0,
            condition = "Cloudy",
            description = "heavy clouds",
            iconUrl = "https://openweathermap.org/img/wn/02d@2x.png"
        )
    )

    override suspend fun getCurrentWeather(latitude: Double, longitude: Double) = currentWeather

    override suspend fun getDailyForecast(latitude: Double, longitude: Double) = dailyForecast
}
