package ru.kestus.thousand_courses.domain

interface PreferencesStorage {

    fun put(key: String, value: String)

    fun get(key: String): String?

    fun remove(key: String)

    companion object {
        const val PREFERENCES_NAME = "ru.kestus.thousand_courses"
        const val KEY_SESSION = "session"
    }

}