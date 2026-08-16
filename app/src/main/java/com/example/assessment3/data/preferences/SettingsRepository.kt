package com.example.assessment3.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "settings"
)

class SettingsRepository(
    private val context: Context
) {

    private object Keys {
        val SOUND_ENABLED = booleanPreferencesKey("sound_enabled")
        val DIFFICULTY = stringPreferencesKey("difficulty")
    }

    val soundEnabled = context.dataStore.data.map { preferences ->
        preferences[Keys.SOUND_ENABLED] ?: true
    }

    val difficulty = context.dataStore.data.map { preferences ->
        preferences[Keys.DIFFICULTY] ?: "Normal"
    }

    suspend fun setSoundEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[Keys.SOUND_ENABLED] = enabled
        }
    }

    suspend fun setDifficulty(difficulty: String) {
        context.dataStore.edit { preferences ->
            preferences[Keys.DIFFICULTY] = difficulty
        }
    }
}