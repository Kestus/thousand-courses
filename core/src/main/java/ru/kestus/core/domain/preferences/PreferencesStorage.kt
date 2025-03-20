package ru.kestus.core.domain.preferences

interface PreferencesStorage {

    suspend fun put(key: String, value: String)

    suspend fun get(key: String): String?

    suspend fun remove(key: String)

}