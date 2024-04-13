package com.narify.weathernowandlater.domain.usecase

import com.narify.weathernowandlater.domain.city.usecase.GetCitiesUseCase
import com.narify.weathernowandlater.domain.testdouble.FakeCityRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class GetCitiesUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var cityRepository: FakeCityRepository

    private lateinit var subject: GetCitiesUseCase

    @Before
    fun setup() {
        cityRepository = FakeCityRepository()
        subject = GetCitiesUseCase(cityRepository)
    }

    @Test
    fun `test get cities`() = testScope.runTest {
        val expectedCities = cityRepository.getCities("Cairo")

        val actualCities = subject(name = "Cairo")

        assertEquals(expectedCities, actualCities)
    }
}
