package com.narify.weathernowandlater.weatherdatatools

import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherTextFormatterTest {
    @Test
    fun `test temperature formatting`() {
        val expectedFormatting = "25°C"

        val actualFormatting = WeatherTextFormatter.addCelsiusWithIntTemperature(25.2)

        assertEquals(expectedFormatting, actualFormatting)
    }

    @Test
    fun `test humidity formatting`() {
        val expectedFormatting = "82%"

        val actualFormatting = WeatherTextFormatter.addPercentageWithIntHumidity(82.46)

        assertEquals(expectedFormatting, actualFormatting)
    }

    @Test
    fun `test wind speed formatting`() {
        val expectedFormatting = "16 m/s"

        val actualFormatting = WeatherTextFormatter.addMPSWithIntWindSpeed(15.8)

        assertEquals(expectedFormatting, actualFormatting)
    }
}
