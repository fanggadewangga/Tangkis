package com.college.tangkis_rpl.informasi_pesan_darurat

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel

class InformasiPesanDaruratControl: ViewModel() {
    fun callULKTSP(activity: InformasiPesanDaruratPage) {
        val uri = Uri.parse("tel:+628113600584")
        val intent = Intent(Intent.ACTION_DIAL, uri)
        activity.startActivity(intent)
    }
}