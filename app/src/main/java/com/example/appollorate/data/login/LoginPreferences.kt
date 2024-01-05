package com.example.appollorate.data.login

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull

class LoginPreferences(private val context: Context) {

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "LocalData")
        val AUTH_KEY = stringPreferencesKey("auth_key")
        val USERNAME = stringPreferencesKey("username")
    }

    suspend fun saveLoginToken(loginToken: String) {
        context.dataStore.edit { LocalData ->
            LocalData[AUTH_KEY] = loginToken
        }
    }

    suspend fun saveUserName(userName: String) {
        context.dataStore.edit { LocalData ->
            LocalData[USERNAME] = userName.uppercase()
        }
    }

    suspend fun getLoginToken(): String? {
        return context.dataStore.data.firstOrNull()?.get(AUTH_KEY)
    }

    suspend fun getUserName(): String? {
        return context.dataStore.data.firstOrNull()?.get(USERNAME)
    }
}
