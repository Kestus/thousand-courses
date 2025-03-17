package ru.kestus.thousand_courses.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import ru.kestus.thousand_courses.domain.PreferencesStorage
import ru.kestus.thousand_courses.domain.PreferencesStorage.Companion.PREFERENCES_NAME

class PreferencesStorageImpl(context: Context): PreferencesStorage {

    private val prefs = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)

    override fun put(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    override fun get(key: String): String? {
        return prefs.getString(key, null)
    }

    override fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }


}