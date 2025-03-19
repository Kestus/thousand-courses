package ru.kestus.core.data.preferences

import android.app.Application
import android.content.Context.MODE_PRIVATE
import ru.kestus.core.domain.preferences.PreferencesStorage
import ru.kestus.core.domain.preferences.PreferencesStorage.Companion.PREFERENCES_NAME
import javax.inject.Inject

class PreferencesStorageImpl @Inject constructor(
    application: Application
) : PreferencesStorage {

    private val prefs = application.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)

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