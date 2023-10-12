package com.college.tangkis.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.college.tangkis.core.util.DatastoreUtil
import com.college.tangkis.core.util.DatastoreUtil.DATASTORE_NAME
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.rootDataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

class TangkisDatastore @Inject constructor(context: Context) {
    private val tangkisDatastore = context.rootDataStore

    suspend fun savePassedOnboardStatus(isPassedOnboard: Boolean) {
        tangkisDatastore.edit {
            it[DatastoreUtil.IS_PASSED_ONBOARD_KEY] = isPassedOnboard
        }
    }

    fun readPassedOnboardStatus() = tangkisDatastore.data.map {
        it[DatastoreUtil.IS_PASSED_ONBOARD_KEY] ?: false
    }
}