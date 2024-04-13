package com.narify.weathernowandlater.data.weather.testdoubles

import com.narify.weathernowandlater.data.weather.datasource.WeatherRemoteDataSource
import com.narify.weathernowandlater.data.weather.model.WeatherDto

internal class FakeWeatherRemoteDataSource : WeatherRemoteDataSource {

    private val weatherDto = WeatherDto(
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


    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        units: String
    ): WeatherDto = weatherDto

    override suspend fun getDailyForecast(
        latitude: Double,
        longitude: Double,
        units: String
    ): WeatherDto = weatherDto
}
