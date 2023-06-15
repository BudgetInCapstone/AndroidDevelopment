package com.bangkit.budgetin.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userAuth")
        private val USER_TOKEN_KEY = booleanPreferencesKey("user_auth")
    }

    val getUserAuth: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[USER_TOKEN_KEY] ?: false
    }

    suspend fun saveUserAuth(status: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = status
        }
    }
}