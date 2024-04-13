package com.narify.weathernowandlater.data.weather.util

import com.narify.weathernowandlater.data.weather.model.WeatherDto
import com.narify.weathernowandlater.domain.weather.model.Weather
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

internal class WeatherMapperTest {

    @Test
    fun `test mapping weather DTO to current weather`() {
        val weatherDto = WeatherDto(
            current = WeatherDto.Current(
                temp = 15.0,
                humidity = 60.0,
                windSpeed = 7.0,
                weatherInfo = listOf(
                    WeatherDto.WeatherInfo(
                        main = "Clear",
                        description = "clear sky",
                        icon = "01d"
                    )
                )
            ),
            daily = null
        )
        val expectedWeather = Weather(
            day = LocalDate.now().dayOfWeek
                .name.lowercase().replaceFirstChar { it.uppercaseChar() },
            temperature = 15.0,
            humidity = 60.0,
            windSpeed = 7.0,
            condition = "Clear",
            description = "clear sky",
            iconUrl = "https://openweathermap.org/img/wn/01d@4x.png"
        )

        val actualWeather = weatherDto.asCurrentWeather()
        assertEquals(expectedWeather, actualWeather)
    }


    @Test
    fun `test mapping weather DTO to daily weather forecast`() {
        val weatherDto = WeatherDto(
            daily = listOf(
                WeatherDto.Daily(
                    temp = WeatherDto.Daily.Temperature(day = 15.0),
                    humidity = 60.0,
                    windSpeed = 7.0,
                    weatherInfo = listOf(
                        WeatherDto.WeatherInfo(
                            main = "Clear",
                            description = "clear sky",
                            icon = "01d"
                        )
                    )
                ),
                WeatherDto.Daily(
                    temp = WeatherDto.Daily.Temperature(day = 12.0),
                    humidity = 40.0,
                    windSpeed = 10.0,
                    weatherInfo = listOf(
                        WeatherDto.WeatherInfo(
                            main = "Cloudy",
                            description = "heavy clouds",
                            icon = "02d"
                        )
                    )
                )
            )
        )

        // The first day is ignored as it is the current day
        val expectedDailyForecast = listOf(
            Weather(
                day = LocalDate.now().dayOfWeek.plus(1)
                    .name.lowercase().replaceFirstChar { it.uppercaseChar() },
                temperature = 12.0,
                humidity = 40.0,
                windSpeed = 10.0,
                condition = "Cloudy",
                description = "heavy clouds",
                iconUrl = "https://openweathermap.org/img/wn/02d@4x.png"
            )
        )

        val actualDailyForecast = weatherDto.asDailyForecast()
        assertEquals(expectedDailyForecast, actualDailyForecast)
    }
}
