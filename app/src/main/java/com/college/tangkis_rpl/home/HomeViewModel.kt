package com.college.tangkis_rpl.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.model.ArtikelInformasi
import com.college.tangkis_rpl.model.KontakDarurat
import com.college.tangkis_rpl.model.Mahasiswa
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _mahasiswaLiveData = MutableLiveData<Mahasiswa>()
    val mahasiswaLiveData: LiveData<Mahasiswa> = _mahasiswaLiveData

    private val _artikelInformasiLiveData = MutableLiveData<List<ArtikelInformasi>>()
    val artikelInformasiLiveData: LiveData<List<ArtikelInformasi>> = _artikelInformasiLiveData

    private val _contactLiveData = MutableLiveData<List<KontakDarurat>>()
    val contactLiveData: LiveData<List<KontakDarurat>> = _contactLiveData

    private val ktorHttpClient = HttpClient(Android) { install(JsonFeature) }

    fun getArticle() {
        val artikelInformasiEntity = ArtikelInformasi()
        viewModelScope.launch {
            _artikelInformasiLiveData.postValue(artikelInformasiEntity.getArticle(ktorHttpClient))
        }
    }

    fun getContacts() {
        val mahasiswaEntity = Mahasiswa()
        viewModelScope.launch {
            _contactLiveData.postValue(
                mahasiswaEntity.getKontakDarurat(
                    "215150200111033",
                    client = ktorHttpClient
                )
            )
        }
    }

    fun getUserData() {
        val mahasiswaEntity = Mahasiswa()
        viewModelScope.launch {
            _mahasiswaLiveData.postValue(
                mahasiswaEntity.getUserDetail(
                    "215150200111033",
                    client = ktorHttpClient
                )
            )
        }
    }
}