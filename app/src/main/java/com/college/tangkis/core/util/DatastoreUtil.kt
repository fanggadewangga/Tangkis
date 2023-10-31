package com.college.tangkis.core.util

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DatastoreUtil {
    const val DATASTORE_NAME = "TANGKIS_DATASTORE"
    val IS_PASSED_ONBOARD_KEY = booleanPreferencesKey("isPassedOnboard")
    val TOKEN = stringPreferencesKey("Token")
    val NIM = stringPreferencesKey("NIM")
}