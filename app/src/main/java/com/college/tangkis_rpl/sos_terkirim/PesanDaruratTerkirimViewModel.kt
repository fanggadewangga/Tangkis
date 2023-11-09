package com.college.tangkis_rpl.sos_terkirim

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel

class PesanDaruratTerkirimViewModel: ViewModel() {
    fun callULKTSP(activity: PesanDaruratTerkirimActivity) {
        val uri = Uri.parse("tel:+6281330723755")
        val intent = Intent(Intent.ACTION_DIAL, uri)
        activity.startActivity(intent)
    }
}