package ru.kestus.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kestus.core.data.preferences.PreferencesStorageImpl
import ru.kestus.core.domain.preferences.PreferencesStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {

    @Binds
    @Singleton
    fun providesSharedPreferences(impl: PreferencesStorageImpl): PreferencesStorage

}