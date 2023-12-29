package com.example.appollorate.data.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey

interface LoginPreferences {
    suspend fun saveLoginToken(loginToken: String)
}
class LoginPreferencesImpl(private val dataStore: DataStore<Preferences>) {
    val AUTH_KEY = stringSetPreferencesKey("auth_key")

    suspend fun saveLoginToken(loginToken: String) {
        dataStore.edit { pref ->
            pref[AUTH_KEY] = setOf(loginToken)
        }
    }
}
