package com.college.tangkis_rpl.article_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.college.tangkis_rpl.databinding.ActivityArticleDetailBinding

class DetailArtikelInformasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailBinding
    private lateinit var judulArtikel: String
    private lateinit var tanggalArtikel: String
    private lateinit var kontenArtikel: String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        judulArtikel = intent.getStringExtra("judul")!!
        tanggalArtikel = intent.getStringExtra("tanggal")!!
        kontenArtikel = intent.getStringExtra("konten")!!
        showArtikelInformasi()
    }

    private fun showArtikelInformasi() {
        binding.apply {
            tvTitleArticleDetail.text = judulArtikel
            tvDateArticleDetail.text = tanggalArtikel
            tvDescArticleDetail.text = kontenArtikel
        }
    }
}