package com.college.tangkis_rpl.artikel_informasi

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.college.tangkis_rpl.home.HomeFragment
import com.college.tangkis_rpl.model.ArtikelInformasi

class ArtikelInformasiViewModel: ViewModel() {
    fun getArtikelInformasi(idArtikel: String, home: HomeFragment) {
        val artikelInformasi = ArtikelInformasi()
        val artikelDipilih = artikelInformasi.getArtikelInformasi(idArtikel)!!
        val intent = Intent(home.requireActivity(), ArtikelInformasiActivity::class.java)
        intent.putExtra("judul", artikelDipilih.judul)
        intent.putExtra("tanggal", artikelDipilih.tanggalUnggah)
        intent.putExtra("gambar", artikelDipilih.image)
        intent.putExtra("konten", artikelDipilih.konten)
        home.requireActivity().startActivity(intent)
    }
}