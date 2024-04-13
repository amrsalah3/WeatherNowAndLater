package com.narify.weathernowandlater.data.weather.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * Representation of OpenWeatherMap API weather response.
 */
@Serializable
data class WeatherDto(
    val lat: Double? = null,
    val lon: Double? = null,
    val timezone: String? = null,
    @SerializedName("timezone_offset") val timezoneOffset: Double? = null,
    val current: Current? = null,
    val daily: List<Daily>? = null,
) {

    @Serializable
    data class WeatherInfo(
        val id: Double? = null,
        val main: String?,
        val description: String?,
        val icon: String?
    )

    @Serializable
    data class Current(
        val dt: Double? = null,
        val sunrise: Double? = null,
        val sunset: Double? = null,
        val temp: Double?,
        @SerializedName("feels_like") val feelsLike: Double? = null,
        val pressure: Double? = null,
        val humidity: Double?,
        @SerializedName("dew_point") val dewPoint: Double? = null,
        val uvi: Double? = null,
        val clouds: Double? = null,
        val visibility: Double? = null,
        @SerializedName("wind_speed") val windSpeed: Double?,
        @SerializedName("wind_deg") val windDeg: Double? = null,
        @SerializedName("wind_gust") val windGust: Double? = null,
        @SerializedName("weather") val weatherInfo: List<WeatherInfo>?,
    )

    @Serializable
    data class Daily(
        val dt: Double? = null,
        val sunrise: Double? = null,
        val sunset: Double? = null,
        val moonrise: Double? = null,
        val moonset: Double? = null,
        @SerializedName("moon_phase") val moonPhase: Double? = null,
        val temp: Temperature? = null,
        @SerializedName("feels_like") val feelsLike: FeelsLike? = null,
        val pressure: Double? = null,
        val humidity: Double?,
        @SerializedName("dew_point") val dewPoint: Double? = null,
        @SerializedName("wind_speed") val windSpeed: Double?,
        @SerializedName("wind_deg") val windDeg: Double? = null,
        @SerializedName("wind_gust") val windGust: Double? = null,
        @SerializedName("weather") val weatherInfo: List<WeatherInfo>?,
        val clouds: Double? = null,
        val pop: Double? = null,
        val uvi: Double? = null
    ) {
        @Serializable
        data class Temperature(
            val day: Double?,
            val min: Double? = null,
            val max: Double? = null,
            val night: Double? = null,
            val eve: Double? = null,
            val morn: Double? = null
        )

        @Serializable
        data class FeelsLike(
            val day: Double?,
            val night: Double?,
            val eve: Double?,
            val morn: Double?
        )
    }
}
