package ru.kestus.core.domain.preferences

interface SessionStorage {

    suspend fun getSession(): String?

    suspend fun setSession(value: String)

    suspend fun removeSession()

}