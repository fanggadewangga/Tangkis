package com.college.tangkis_rpl.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.model.ArtikelInformasi
import com.college.tangkis_rpl.model.KontakDarurat
import com.college.tangkis_rpl.model.Mahasiswa
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    fun getDataMahasiswa(home: HomeFragment) {
        var mahasiswa: Mahasiswa? = Mahasiswa()
        viewModelScope.launch {
            mahasiswa = mahasiswa!!.getProfilData()
            mahasiswa?.let { home.showDataMahasiswa(it) }
        }
    }

    fun getKontakDarurat(home: HomeFragment) {
        var kontakDarurat: List<KontakDarurat>
        viewModelScope.launch {
            val mahasiswa = Mahasiswa()
            kontakDarurat = mahasiswa.getKontakDarurat()
            home.showKontakDarurat(kontakDarurat)
        }
    }

    fun getDaftarArtikelInformasi(): List<ArtikelInformasi> {
        return ArtikelInformasi().getDaftarArtikel()
    }
}