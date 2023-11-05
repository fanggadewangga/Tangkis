package com.college.tangkis_rpl.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.model.KontakDarurat
import com.college.tangkis_rpl.model.Mahasiswa
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import kotlinx.coroutines.launch

class ContactViewModel: ViewModel() {
    private val _contactLiveData = MutableLiveData<List<KontakDarurat>>()
    val contactLiveData: LiveData<List<KontakDarurat>> = _contactLiveData

    private val ktorHttpClient = HttpClient(Android) { install(JsonFeature) }

    fun getContacts() {
        val mahasiswaEntity = Mahasiswa()
        viewModelScope.launch {
            _contactLiveData.postValue(
                mahasiswaEntity.getContacts(
                    "215150200111033",
                    client = ktorHttpClient
                )
            )
        }
    }

    init {
        getContacts()
    }
}