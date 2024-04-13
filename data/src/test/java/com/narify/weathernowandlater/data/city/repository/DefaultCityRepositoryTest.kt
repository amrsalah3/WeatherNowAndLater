package com.narify.weathernowandlater.data.city.repository

import com.narify.weathernowandlater.data.city.testdouble.FakeCityLocalDataSource
import com.narify.weathernowandlater.data.city.testdouble.FakeCityRemoteDataSource
import com.narify.weathernowandlater.data.city.util.asCityList
import com.narify.weathernowandlater.domain.city.model.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class DefaultCityRepositoryTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var cityLocalDataSource: FakeCityLocalDataSource

    private lateinit var cityRemoteDataSource: FakeCityRemoteDataSource

    private lateinit var subject: DefaultCityRepository

    @Before
    fun setup() {
        cityLocalDataSource = FakeCityLocalDataSource()
        cityRemoteDataSource = FakeCityRemoteDataSource()

        subject = DefaultCityRepository(
            localDataSource = cityLocalDataSource,
            remoteDataSource = cityRemoteDataSource,
            dispatcher = Dispatchers.IO
        )
    }

    @Test
    fun `test get cities by name`() = testScope.runTest {
        val expectedCities = cityRemoteDataSource.getCities("London").asCityList()

        val actualCities = subject.getCities("London")

        assertEquals(expectedCities, actualCities)
    }

    @Test
    fun `test save city locally`() = testScope.runTest {
        val expectedCity = City(
            name = "Cairo",
            fullLocation = "Cairo, Egypt",
            latitude = 30.0,
            longitude = 31.0,
        )

        subject.saveCity(expectedCity)

        val actualCity = cityLocalDataSource.getSavedCity().first()
        assertEquals(expectedCity, actualCity)
    }

    @Test
    fun `test get the locally saved city`() = testScope.runTest {
        val expectedCity = City(
            name = "Cairo",
            fullLocation = "Cairo, Egypt",
            latitude = 30.0,
            longitude = 31.0,
        )
        cityLocalDataSource.saveCity(expectedCity)

        val actualCity = subject.getSavedCity().first()

        assertEquals(expectedCity, actualCity)
    }
}
