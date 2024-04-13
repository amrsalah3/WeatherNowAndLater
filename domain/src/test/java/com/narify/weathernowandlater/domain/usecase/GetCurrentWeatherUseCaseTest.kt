package com.narify.weathernowandlater.domain.usecase

import com.narify.weathernowandlater.domain.city.model.City
import com.narify.weathernowandlater.domain.city.usecase.GetUserSavedCityUseCase
import com.narify.weathernowandlater.domain.testdouble.FakeCityRepository
import com.narify.weathernowandlater.domain.testdouble.FakeWeatherRepository
import com.narify.weathernowandlater.domain.weather.usecase.GetCurrentWeatherUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

internal class GetCurrentWeatherUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var getUserSavedCityUseCase: GetUserSavedCityUseCase
    private lateinit var weatherRepository: FakeWeatherRepository
    private lateinit var cityRepository: FakeCityRepository

    private lateinit var subject: GetCurrentWeatherUseCase

    @Before
    fun setup() {
        weatherRepository = FakeWeatherRepository()
        cityRepository = FakeCityRepository()
        getUserSavedCityUseCase = GetUserSavedCityUseCase(cityRepository)

        subject = GetCurrentWeatherUseCase(weatherRepository, getUserSavedCityUseCase)
    }

    @Test
    fun `test get current weather with no saved city`() = testScope.runTest {
        assertFails { subject().first() }
    }

    @Test
    fun `test get current weather with saved city`() = testScope.runTest {
        val any = 10.0
        cityRepository.saveCity(
            City("Any city", "Any location", any, any)
        )
        val expectedWeather = weatherRepository.getCurrentWeather(any, any)

        val actualWeather = subject().first()

        assertEquals(expectedWeather, actualWeather)
    }
}
