package com.narify.weathernowandlater.domain.usecase

import com.narify.weathernowandlater.domain.city.model.City
import com.narify.weathernowandlater.domain.city.usecase.GetUserSavedCityUseCase
import com.narify.weathernowandlater.domain.testdouble.FakeCityRepository
import com.narify.weathernowandlater.domain.testdouble.FakeWeatherRepository
import com.narify.weathernowandlater.domain.weather.usecase.GetDailyForecastUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

internal class GetDailyForecastUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var getUserSavedCityUseCase: GetUserSavedCityUseCase
    private lateinit var weatherRepository: FakeWeatherRepository
    private lateinit var cityRepository: FakeCityRepository

    private lateinit var subject: GetDailyForecastUseCase

    @Before
    fun setup() {
        weatherRepository = FakeWeatherRepository()
        cityRepository = FakeCityRepository()
        getUserSavedCityUseCase = GetUserSavedCityUseCase(cityRepository)

        subject = GetDailyForecastUseCase(weatherRepository, getUserSavedCityUseCase)
    }

    @Test
    fun `test get daily forecast with no saved city`() = testScope.runTest {
        assertFails { subject().first() }
    }

    @Test
    fun `test get daily forecast with saved city`() = testScope.runTest {
        val any = 10.0
        cityRepository.saveCity(
            City("Any city", "Any location", any, any)
        )
        val expectedForecast = weatherRepository.getDailyForecast(any, any)

        val actualForecast = subject()

        assertEquals(expectedForecast, actualForecast)
    }
}
