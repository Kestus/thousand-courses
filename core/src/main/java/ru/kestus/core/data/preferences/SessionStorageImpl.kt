package ru.kestus.core.data.preferences

import android.app.Application
import android.content.Context.MODE_PRIVATE
import ru.kestus.core.domain.preferences.SessionStorage
import javax.inject.Inject

class SessionStorageImpl @Inject constructor(
    application: Application
) : SessionStorage {

    private val prefsName = application.packageName
    private val prefs = application.getSharedPreferences(prefsName, MODE_PRIVATE)

    override suspend fun getSession(): String? {
        return prefs.getString(KEY_SESSION, null)
    }

    override suspend fun setSession(value: String) {
        prefs.edit().putString(KEY_SESSION, value).apply()
    }

    override suspend fun removeSession() {
        prefs.edit().remove(KEY_SESSION).apply()
    }

    companion object {
        private const val KEY_SESSION = "session"
    }
}