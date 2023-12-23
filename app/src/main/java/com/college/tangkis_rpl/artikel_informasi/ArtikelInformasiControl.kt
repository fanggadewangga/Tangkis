package com.college.tangkis_rpl.artikel_informasi

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.college.tangkis_rpl.home.HomePage
import com.college.tangkis_rpl.model.ArtikelInformasi

class ArtikelInformasiControl: ViewModel() {
    fun getArtikelInformasi(idArtikel: String, home: HomePage) {
        val artikelInformasi = ArtikelInformasi()
        val artikelDipilih = artikelInformasi.getArtikelInformasi(idArtikel)!!
        val intent = makeIntent(home.requireActivity(), ArtikelInformasiPage::class.java)
        intent.putExtra("judul", artikelDipilih.getJudul())
        intent.putExtra("tanggal", artikelDipilih.getTanggalUnggah())
        intent.putExtra("gambar", artikelDipilih.getImage())
        intent.putExtra("konten", artikelDipilih.getKonten())
        home.requireActivity().startActivity(intent)
    }

    private fun makeIntent(context: android.content.Context, target: Class<ArtikelInformasiPage>): Intent {
        return Intent(context, target)
    }
}