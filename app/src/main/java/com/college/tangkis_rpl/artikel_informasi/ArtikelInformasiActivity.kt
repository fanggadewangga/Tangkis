package com.college.tangkis_rpl.artikel_informasi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.college.tangkis_rpl.databinding.ActivityArtikelInformasiBinding

class ArtikelInformasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtikelInformasiBinding
    private lateinit var judulArtikel: String
    private lateinit var tanggalArtikel: String
    private lateinit var kontenArtikel: String
    private var gambarArtikel: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityArtikelInformasiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        judulArtikel = intent.getStringExtra("judul")!!
        tanggalArtikel = intent.getStringExtra("tanggal")!!
        kontenArtikel = intent.getStringExtra("konten")!!
        gambarArtikel = intent.getIntExtra("gambar", 0)
        showArtikelInformasi()
    }

    private fun showArtikelInformasi() {
        binding.apply {
            tvTitleArticleDetail.text = judulArtikel
            tvDateArticleDetail.text = tanggalArtikel
            tvDescArticleDetail.text = kontenArtikel
            ivArticleDetail.setImageResource(gambarArtikel)
        }
    }
}