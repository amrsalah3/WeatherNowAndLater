package com.narify.weathernowandlater.core.di

import com.narify.weathernowandlater.data.city.datasource.CityLocalDataSource
import com.narify.weathernowandlater.data.city.datasource.CityRemoteDataSource
import com.narify.weathernowandlater.data.city.datasource.DefaultCityLocalDataSource
import com.narify.weathernowandlater.data.city.repository.DefaultCityRepository
import com.narify.weathernowandlater.data.city.util.CityApiConstants
import com.narify.weathernowandlater.domain.city.repository.CityRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CityDataModule {

    @Binds
    abstract fun bindCityLocalDataSource(
        cityLocalDataSource: DefaultCityLocalDataSource,
    ): CityLocalDataSource

    @Binds
    abstract fun bindCityRepository(
        cityRepository: DefaultCityRepository,
    ): CityRepository

    companion object {
        @Qualifier
        annotation class CityApiClient

        @Provides
        @Singleton
        @CityApiClient
        fun provideCityApiRetrofitClient(): Retrofit = Retrofit.Builder()
            .baseUrl(CityApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @Provides
        @Singleton
        fun provideCityRemoteDataSource(@CityApiClient retrofit: Retrofit)
                : CityRemoteDataSource = retrofit.create(CityRemoteDataSource::class.java)
    }
}
