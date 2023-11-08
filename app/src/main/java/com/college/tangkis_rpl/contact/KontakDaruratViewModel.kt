package com.college.tangkis_rpl.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.model.Mahasiswa
import kotlinx.coroutines.launch

class KontakDaruratViewModel: ViewModel() {
    fun getKontakDarurat(activity: KontakDaruratActivity? = null) {
        viewModelScope.launch {
            val mahasiswa = Mahasiswa()
            val kontakDarurat = mahasiswa.getKontakDarurat()
            if (kontakDarurat.isEmpty())
                activity?.showEmpty()
            else
                activity?.showDaftarKontak(kontakDarurat)
        }
    }
}