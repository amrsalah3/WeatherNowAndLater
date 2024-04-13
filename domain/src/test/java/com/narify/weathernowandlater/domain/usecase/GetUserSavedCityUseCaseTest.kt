package com.narify.weathernowandlater.domain.usecase

import com.narify.weathernowandlater.domain.city.model.City
import com.narify.weathernowandlater.domain.city.usecase.GetUserSavedCityUseCase
import com.narify.weathernowandlater.domain.testdouble.FakeCityRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class GetUserSavedCityUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var cityRepository: FakeCityRepository

    private lateinit var subject: GetUserSavedCityUseCase

    @Before
    fun setup() {
        cityRepository = FakeCityRepository()
        subject = GetUserSavedCityUseCase(cityRepository)
    }

    @Test
    fun `test get user saved city`() = testScope.runTest {
        val expectedCity = City(
            name = "Cairo",
            fullLocation = "Cairo, Egypt",
            latitude = 30.0,
            longitude = 30.0,
        )
        cityRepository.saveCity(expectedCity)

        val actualCity = subject().first()

        assertEquals(expectedCity, actualCity)
    }
}
