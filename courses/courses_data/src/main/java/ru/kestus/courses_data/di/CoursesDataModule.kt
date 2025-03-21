package ru.kestus.courses_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kestus.courses_data.ApiResponseMapper

@Module
@InstallIn(SingletonComponent::class)
class CoursesDataModule {

    @Provides
    fun provideApiResponseMapper() = ApiResponseMapper


}