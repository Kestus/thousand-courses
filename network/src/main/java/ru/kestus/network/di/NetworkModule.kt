package ru.kestus.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kestus.network.ApiFactory
import ru.kestus.network.ApiService

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiFactory.getService()
    }

}