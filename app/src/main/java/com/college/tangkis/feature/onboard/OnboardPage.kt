package com.college.tangkis.feature.onboard

import androidx.annotation.DrawableRes
import com.college.tangkis.R

sealed class OnboardPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
) {
    data object First : OnboardPage(
        image = R.drawable.iv_onboard_1,
        title = "Pesan Darurat",
        description = "Mintalah bantuan ke beberapa kontak sekaligus hanya dengan sekali tekan"
    )

    data object Second : OnboardPage(
        image = R.drawable.iv_onboard_2,
        title = "Kontak Darurat",
        description = "Tambahkan kontak darurat agar bisa membantumu kapan saja"
    )

    data object Third : OnboardPage(
        image = R.drawable.iv_onboard_3,
        title = "Artikel Informasi",
        description = "Bacalah artikel informasi sehingga dapat membantumu"
    )
}
