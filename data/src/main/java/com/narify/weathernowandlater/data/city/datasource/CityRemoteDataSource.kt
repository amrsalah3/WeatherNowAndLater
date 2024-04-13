package com.narify.weathernowandlater.data.city.datasource

import com.narify.weathernowandlater.data.city.model.CityDto
import com.narify.weathernowandlater.data.city.util.CityApiConstants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface CityRemoteDataSource {

    @GET("direct?appid=$API_KEY")
    suspend fun getCities(
        @Query("q") name: String,
        @Query("limit") limit: Int = 20,
    ): List<CityDto>
}
