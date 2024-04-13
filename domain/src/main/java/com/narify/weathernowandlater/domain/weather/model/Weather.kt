package com.narify.weathernowandlater.domain.weather.model

data class Weather(
    val day: String,
    val temperature: Double,
    val humidity: Double,
    val windSpeed: Double,
    val condition: String,
    val description: String,
    val iconUrl: String
) {
    companion object {
        fun empty() = Weather(
            day = "",
            temperature = 0.0,
            humidity = 0.0,
            windSpeed = 0.0,
            condition = "",
            description = "",
            iconUrl = "",
        )
    }
}
