package com.narify.weathernowandlater.weatherdatatools

import kotlin.math.roundToInt

object WeatherTextFormatter {

    fun addCelsiusWithIntTemperature(temperature: Double) = "${temperature.roundToInt()}°C"

    fun addPercentageWithIntHumidity(humidity: Double) = "${humidity.roundToInt()}%"

    fun addMPSWithIntWindSpeed(windSpeed: Double) = "${windSpeed.roundToInt()} m/s"
}
