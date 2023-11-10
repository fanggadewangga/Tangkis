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
        intent.putExtra("judul", artikelDipilih.title)
        intent.putExtra("tanggal", artikelDipilih.postDate)
        intent.putExtra("gambar", artikelDipilih.imageUrl)
        intent.putExtra("konten", artikelDipilih.content)
        home.requireActivity().startActivity(intent)
    }
}