package ru.kestus.courses_di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kestus.courses_data.RepositoryImpl
import ru.kestus.courses_domain.Repository

@Module
@InstallIn(SingletonComponent::class)
interface CoursesModule {

    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository

}