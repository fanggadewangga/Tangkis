package com.college.tangkis.core.util

import androidx.datastore.preferences.core.booleanPreferencesKey

object DatastoreUtil {
    const val DATASTORE_NAME = "TANGKIS_DATASTORE"
    val IS_PASSED_ONBOARD_KEY = booleanPreferencesKey("isPassedOnboard")
}