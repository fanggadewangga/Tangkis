package com.college.tangkis_rpl.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.model.KontakDarurat
import com.college.tangkis_rpl.model.Mahasiswa
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    fun getDataMahasiswa(home: HomeFragment) {
        var mahasiswa: Mahasiswa? = Mahasiswa()
        viewModelScope.launch {
            mahasiswa = mahasiswa!!.getProfilData()
            home.showDataMahasiswa(mahasiswa!!)
        }
    }

    fun getKontakDarurat(home: HomeFragment) {
        var kontakDarurat: List<KontakDarurat> = emptyList()
        viewModelScope.launch {
            val mahasiswa = Mahasiswa()
            kontakDarurat = mahasiswa.getKontakDarurat()
            home.showKontakDarurat(kontakDarurat)
        }
    }

}