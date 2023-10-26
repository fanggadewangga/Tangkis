package com.college.tangkis.feature.onboard

import androidx.annotation.DrawableRes
import com.college.tangkis.R

sealed class OnboardPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
) {
    data object First : OnboardPage(
        image = R.drawable.iv_onboard_sos,
        title = "Emergency SOS",
        description = "Mintalah bantuan ke beberapa kontak sekaligus hanya dengan sekali tekan"
    )

    data object Second : OnboardPage(
        image = R.drawable.iv_onboard_report,
        title = "Pelaporan Online",
        description = "Laporkan kasus kekerasan seksual secara mudah dan cepat"
    )

    data object Third : OnboardPage(
        image = R.drawable.iv_onboard_consult,
        title = "Konsultasi",
        description = "Mintalah dukungan maupun saran untuk membantu pemulihan mental"
    )
}
