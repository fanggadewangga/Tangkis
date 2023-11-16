package com.college.tangkis.feature.faq

import androidx.lifecycle.ViewModel
import com.college.tangkis.domain.model.faq.Faq
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FaqViewModel @Inject constructor() : ViewModel() {
    val listFaq = listOf(
        Faq(
            question = "Bagaimana cara menggunakan SOS?",
            answer = "Kamu dapat menggunakan tombol SOS ketika Anda menghadapi potensi bahaya pelecehan seksual di sekitar area Fakultas Ilmu Komputer."
        ),
        Faq(
            question = "Siapa yang harus ditambahkan dalam kontak darurat?",
            answer = "Sebaiknya tambahkan nomor kontak teman atau orang yang kamu kenal di Fakultas Ilmu Komputer UB agar pertolongan lebih optimal dan memastikan efisiensi dan efektivitas dalam situasi darurat."
        ),
        Faq(
            question = "Apakah kerahasiaan data saya terjamin?",
            answer = "Tidak perlu khawatir, privasi dan kerahasiaan Kamu adalah prioritas kami. Segala data dan identitas hanya digunakan untuk kepentingan konseling."
        ),
        Faq(
            question = "Siapa yang akan melayani saat konseling?",
            answer = "Saat konseling kamu akan dilayani oleh psikolog dari fakultas atau teman sebaya yang sudah terverifikasi keahlianya."
        )
    )
}