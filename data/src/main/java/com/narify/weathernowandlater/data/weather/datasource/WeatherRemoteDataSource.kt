package com.narify.weathernowandlater.data.weather.datasource

import com.narify.weathernowandlater.data.weather.model.WeatherDto
import com.narify.weathernowandlater.data.weather.util.WeatherApiConstants.API_KEY
import com.narify.weathernowandlater.data.weather.util.WeatherApiConstants.EXCLUDE_ALL_BUT_CURRENT
import com.narify.weathernowandlater.data.weather.util.WeatherApiConstants.EXCLUDE_ALL_BUT_DAILY
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRemoteDataSource {

    @GET("onecall?exclude=$EXCLUDE_ALL_BUT_CURRENT&appid=$API_KEY")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric"
    ): WeatherDto

    @GET("onecall?exclude=$EXCLUDE_ALL_BUT_DAILY&appid=$API_KEY")
    suspend fun getDailyForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric"
    ): WeatherDto
}
