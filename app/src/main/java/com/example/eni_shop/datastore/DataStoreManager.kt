package com.example.eni_shop.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object DataStoreManager {

    private val Context.datastore by preferencesDataStore("settings")
    private val DARK_MODE_KEY = booleanPreferencesKey("DARK_MODE_KEY")

    suspend fun setDarkMode(context: Context, isEnabled: Boolean) {
        context.datastore.edit { pref ->
            pref[DARK_MODE_KEY] = isEnabled
        }
    }

    suspend fun isDarkModeEnabled(context: Context): Flow<Boolean> {
        return context.datastore.data.map { pref ->
            pref[DARK_MODE_KEY] ?: false
        }
    }


}