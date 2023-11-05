package com.college.tangkis_rpl.datastore

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class UserDataStore(private val context: Context) {
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: UserDataStore? = null

        fun getInstance(context: Context): UserDataStore =
            instance ?: synchronized(this) {
                val newInstance = instance ?: UserDataStore(context).also { instance = it }
                newInstance
            }
    }

    private val Context.userPreferenceDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "Tangkis"
    )

    suspend fun saveToken(token: String) {
        val tokenKey = stringPreferencesKey("TOKEN")
        context.userPreferenceDataStore.edit {
            it[tokenKey] = "Bearer $token"
        }
    }

    fun readToken(): String {
        val tokenKey = stringPreferencesKey("TOKEN")
        return context.userPreferenceDataStore.data.map { preferences ->
            preferences[tokenKey] ?: ""
        }.toString()
    }

    suspend fun saveNim(nim: String) {
        val nimKey = stringPreferencesKey("NIM")
        context.userPreferenceDataStore.edit {
            it[nimKey] = nim
        }
    }

    fun readNim(): String {
        val nimKey = stringPreferencesKey("NIM")
        return context.userPreferenceDataStore.data.map { preferences ->
            preferences[nimKey] ?: ""
        }.toString()
    }
}