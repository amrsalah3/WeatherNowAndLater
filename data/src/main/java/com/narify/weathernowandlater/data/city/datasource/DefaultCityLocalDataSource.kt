package com.narify.weathernowandlater.data.city.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.narify.weathernowandlater.domain.city.model.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultCityLocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : CityLocalDataSource {

    private val cityKey = stringPreferencesKey("city")

    override suspend fun saveCity(city: City) {
        dataStore.edit { preferences ->
            preferences[cityKey] = Json.encodeToString(city)
        }
    }

    override fun getSavedCity(): Flow<City> {
        return dataStore.data.map { preferences ->
            val encodedCity = preferences[cityKey]!!
            return@map Json.decodeFromString(encodedCity)
        }
    }

    internal companion object {
        const val CITY_PREF_FILE_NAME = "user_city"
    }
}
