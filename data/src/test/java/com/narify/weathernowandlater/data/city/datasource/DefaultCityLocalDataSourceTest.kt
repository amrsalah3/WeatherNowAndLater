package com.narify.weathernowandlater.data.city.datasource

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.narify.weathernowandlater.domain.city.model.City
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import kotlin.test.assertEquals

internal class DefaultCityLocalDataSourceTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(UnconfinedTestDispatcher())

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    private lateinit var subject: DefaultCityLocalDataSource

    @Before
    fun setup() {
        subject = DefaultCityLocalDataSource(tmpFolder.testUserCityDataStore())
    }

    @Test
    fun `test save and get city`() = testScope.runTest {
        val expectedCity = City(
            name = "Cairo",
            fullLocation = "Cairo, Egypt",
            latitude = 30.0,
            longitude = 30.0,
        )

        subject.saveCity(expectedCity)
        val actualCity = subject.getSavedCity().first()

        assertEquals(expectedCity, actualCity)
    }

    private fun TemporaryFolder.testUserCityDataStore() =
        PreferenceDataStoreFactory.create {
            newFile("user_city_test.preferences_pb")
        }
}
