package com.college.tangkis_rpl.home

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.article_detail.DetailArtikelInformasiActivity
import com.college.tangkis_rpl.model.ArtikelInformasi
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
        var kontakDarurat: List<KontakDarurat>
        viewModelScope.launch {
            val mahasiswa = Mahasiswa()
            kontakDarurat = mahasiswa.getKontakDarurat()
            home.showKontakDarurat(kontakDarurat)
        }
    }

    fun getArtikelInformasi(idArtikel: String, home: HomeFragment) {
        val artikelInformasi = ArtikelInformasi()
        val artikelDipilih = artikelInformasi.getArtikelInformasi(idArtikel)!!
        val intent = Intent(home.requireActivity(), DetailArtikelInformasiActivity::class.java)
        intent.putExtra("judul", artikelDipilih.title)
        intent.putExtra("tanggal", artikelDipilih.postDate)
        intent.putExtra("gambar", artikelDipilih.imageUrl)
        intent.putExtra("konten", artikelDipilih.content)
        home.requireActivity().startActivity(intent)
    }

    fun getDaftarArtikelInformasi(): List<ArtikelInformasi> {
        return ArtikelInformasi().getDaftarArtikel()
    }
}