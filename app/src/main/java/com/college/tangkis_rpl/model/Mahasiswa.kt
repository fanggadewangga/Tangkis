package com.college.tangkis_rpl.model

import android.telephony.SmsManager
import android.util.Log
import com.college.tangkis_rpl.firebase.Firebase
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@Suppress("DEPRECATION")
data class Mahasiswa(
    val nim: String = "",
    val nama: String = "",
    val password: String = "",
) {
    suspend fun getProfilData(): Mahasiswa? {
        Log.d("Get Data Mahasiswa", "Masuk")
        var mahasiswa: Mahasiswa? = null
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val firebaseFirestore = FirebaseFirestore.getInstance()
        val nim = firebaseAuthentication.currentUser!!.email!!.substringBefore("@")

        try {
            val querySnapshot =
                firebaseFirestore.collection("Mahasiswa").whereEqualTo("nim", nim).get().await()
            Log.d("Get Data Mahasiswa", querySnapshot.toString())
            if (!querySnapshot.isEmpty) {
                for (document in querySnapshot.documents) {
                    val nimMahasiswa = document.getString("nim")
                    val namaMahasiswa = document.getString("nama")
                    val password = document.getString("password")
                    mahasiswa = Mahasiswa(nimMahasiswa!!, namaMahasiswa!!, password!!)
                    Log.d("Get Data Mahasiswa", mahasiswa.toString())
                }
            }
        } catch (exception: Exception) {
            Log.e("Get Data Mahasiswa: $exception", "True")
        }
        return mahasiswa
    }

    fun tambahKontak(kontak: KontakDarurat): String? {
        var errorMessage: String? = ""
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val firebaseFirestore = FirebaseFirestore.getInstance()
        val nim = firebaseAuthentication.currentUser!!.email!!.substringBefore("@")
        var jumlahKontak = 0
        val kontakCollection = firebaseFirestore.collection("KontakDarurat")
        kontakCollection.whereEqualTo("nim", nim).get()
            .addOnSuccessListener { querySnapshot ->
                jumlahKontak = querySnapshot.size()
            }

        if (jumlahKontak < 5) {
            val kontakCollection = firebaseFirestore.collection("KontakDarurat")
            val nomorKontakBaru = kontak.nomor

            if (!kontakCollection.whereEqualTo("nim", nim).whereEqualTo("nomor", nomorKontakBaru)
                    .get().result.isEmpty
            ) {
                errorMessage = "Nomor kontak sudah ada sebelumnya."
            } else {
                val kontakBaru = hashMapOf(
                    "nim" to nim,
                    "nomor" to kontak.nomor,
                    "nama" to kontak.nama
                )

                kontakCollection.add(kontakBaru)
                    .addOnFailureListener {
                        errorMessage = "Gagal menambahkan kontak darurat."
                    }
            }
        } else {
            errorMessage = "Hanya dapat menambahkan maksimal 5 kontak darurat."
        }
        return errorMessage
    }

    suspend fun deleteKontak(nomor: String): Boolean {
        Log.d("HAPUS KONTAK", "MASUK")
        var isError = false
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val firebaseFirestore = FirebaseFirestore.getInstance()

        try {Log.d("HAPUS KONTAK", "MASUK TRY")
            val nim = firebaseAuthentication.currentUser!!.email!!.substringBefore("@")
            val querySnapshot = firebaseFirestore.collection("KontakDarurat")
                .whereEqualTo("nim", nim)
                .whereEqualTo("nomor", nomor)
                .get()
                .await()
            Log.d("HAPUS KONTAK", nomor)

            if (!querySnapshot.isEmpty) {
                for (document in querySnapshot) {
                    document.reference.delete().await()
                }
            } else {
                isError = true
            }
        } catch (exception: Exception) {
            isError = true
            Log.d("HAPUS KONTAK", exception.message.toString())
        }

        return isError
    }

    suspend fun getKontakDarurat(): List<KontakDarurat> {
        Log.d("GET DAFTAR KONTAK", "MASUK")
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val firebaseFirestore = FirebaseFirestore.getInstance()
        val kontakCollection = firebaseFirestore.collection("KontakDarurat")
        val kontakDarurat = mutableListOf<KontakDarurat>()
        val nim = firebaseAuthentication.currentUser!!.email!!.substringBefore("@")

        try {
            Log.d("GET DAFTAR KONTAK", "MASUK TRY")
            val querySnapshot = kontakCollection.whereEqualTo("nim", nim).get().await()
            for (document in querySnapshot) {
                val nomor = document.getString("nomor")
                val nama = document.getString("nama")
                if (nomor != null && nama != null) {
                    val kontak = KontakDarurat(nama, nomor)
                    Log.d("GET DAFTAR KONTAK", kontak.toString())
                    kontakDarurat.add(kontak)
                }
            }
        } catch (exception: Exception) {
            Log.d("GET DAFTAR KONTAK", exception.toString())
        }

        return kontakDarurat.toList()
    }

    suspend fun sendPesanDarurat(daftarKontakDarurat: List<KontakDarurat>, lokasi: LatLng) {
        val smsManager = SmsManager.getDefault()
        val mahasiswa = getProfilData()
        val message =
            "Dikirim secara otomatis oleh Tangkis: Tangani Kekerasan Seksual: \n\n\nAnda menerima pesan ini karena nomor Anda tercantum sebagai kontak darurat ${mahasiswa?.nama}. \n\n ${mahasiswa?.nama!!.split(" ")[0]} menggunakan fitur pesan darurat di aplikasi Tangkis dan memerlukan bantuan. \n\n ${mahasiswa.nama.split(" ")[0]} membagikan lokasi dengan Anda: https://www.google.com/maps?q=${lokasi.latitude},${lokasi.longitude}. \n\n\nHubungi ${mahasiswa.nama.split(" ")[0]} langsung untuk mendapat pembaruan."
        val parts = smsManager.divideMessage(message)
        daftarKontakDarurat.forEach {
            smsManager.sendMultipartTextMessage(
                it.nomor,
                null,
                parts,
                null,
                null
            )
        }
    }

    fun logout() {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
    }
}
