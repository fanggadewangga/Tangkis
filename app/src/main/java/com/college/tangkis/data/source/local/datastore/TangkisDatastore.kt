package com.college.tangkis.data.source.local.datastore

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

    suspend fun saveBearerToken(token: String) {
        tangkisDatastore.edit {
            it[DatastoreUtil.TOKEN] = token
        }
    }

    suspend fun saveNIM(nim: String) {
        tangkisDatastore.edit {
            it[DatastoreUtil.NIM] = nim
        }
    }

    fun readPassedOnboardStatus() = tangkisDatastore.data.map {
        it[DatastoreUtil.IS_PASSED_ONBOARD_KEY] ?: false
    }

    fun readBearerToken() = tangkisDatastore.data.map {
        it[DatastoreUtil.TOKEN] ?: ""
    }

    fun readNIM() = tangkisDatastore.data.map {
        it[DatastoreUtil.NIM] ?: ""
    }

    suspend fun deleteToken() {
        tangkisDatastore.edit {
            it.remove(DatastoreUtil.TOKEN)
        }
    }

    suspend fun deleteNIM() {
        tangkisDatastore.edit {
            it.remove(DatastoreUtil.NIM)
        }
    }
}