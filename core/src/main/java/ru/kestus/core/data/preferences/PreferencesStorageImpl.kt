package ru.kestus.core.data.preferences

import android.app.Application
import android.content.Context.MODE_PRIVATE
import ru.kestus.core.domain.preferences.PreferencesStorage
import javax.inject.Inject

class PreferencesStorageImpl @Inject constructor(
    application: Application
) : PreferencesStorage {

    private val prefsName = application.packageName
    private val prefs = application.getSharedPreferences(prefsName, MODE_PRIVATE)

    override suspend fun put(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    override suspend fun get(key: String): String? {
        return prefs.getString(key, null)
    }

    override suspend fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }

}