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

class KontakDaruratViewModel: ViewModel() {
    private val _kontakDarurat = MutableLiveData<List<KontakDarurat>>()
    val kontakDarurat: LiveData<List<KontakDarurat>> = _kontakDarurat

    private val ktorHttpClient = HttpClient(Android) { install(JsonFeature) }

    fun getKontakDarurat() {
        val mahasiswa = Mahasiswa()
        viewModelScope.launch {
            _kontakDarurat.postValue(
                mahasiswa.getKontakDarurat(
                    "215150200111033",
                    client = ktorHttpClient
                )
            )
        }
    }

    init {
        getKontakDarurat()
    }
}