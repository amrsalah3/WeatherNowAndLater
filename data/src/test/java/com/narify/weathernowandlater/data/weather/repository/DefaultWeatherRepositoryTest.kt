package com.narify.weathernowandlater.data.weather.repository

import com.narify.weathernowandlater.data.weather.testdoubles.FakeWeatherRemoteDataSource
import com.narify.weathernowandlater.data.weather.util.asCurrentWeather
import com.narify.weathernowandlater.data.weather.util.asDailyForecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class DefaultWeatherRepositoryTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var weatherRemoteDataSource: FakeWeatherRemoteDataSource

    private lateinit var subject: DefaultWeatherRepository

    @Before
    fun setup() {
        weatherRemoteDataSource = FakeWeatherRemoteDataSource()

        subject = DefaultWeatherRepository(
            remoteDataSource = weatherRemoteDataSource,
            dispatcher = Dispatchers.IO
        )
    }

    @Test
    fun `test get current weather`() = testScope.runTest {
        val any = 10.0
        val expectedWeather =
            weatherRemoteDataSource.getCurrentWeather(any, any, "").asCurrentWeather()

        val actualWeather = subject.getCurrentWeather(any, any)

        assertEquals(expectedWeather, actualWeather)
    }

    @Test
    fun `test get daily weather forecast`() = testScope.runTest {
        val any = 10.0
        val expectedForecast =
            weatherRemoteDataSource.getDailyForecast(any, any, "").asDailyForecast()

        val actualForecast = subject.getDailyForecast(any, any)

        assertEquals(expectedForecast, actualForecast)
    }
}
