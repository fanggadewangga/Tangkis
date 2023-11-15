package com.college.tangkis_rpl.model

import android.telephony.SmsManager
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@Suppress("DEPRECATION")
class Mahasiswa(
    val nim: String = "",
    val nama: String = "",
    val password: String = "",
) {
    suspend fun getProfilData(): Mahasiswa? {
        var mahasiswa: Mahasiswa? = null
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val firebaseFirestore = FirebaseFirestore.getInstance()
        val mahasiswaCollection = firebaseFirestore.collection("Mahasiswa")
        val nim = firebaseAuthentication.currentUser!!.email!!.substringBefore("@")

        try {
            val querySnapshot =
                mahasiswaCollection.whereEqualTo("nim", nim).get().await()
            Log.d("Get Data Mahasiswa", querySnapshot.toString())
            if (!querySnapshot.isEmpty) {
                for (document in querySnapshot.documents) {
                    val nimMahasiswa = document.getString("nim")
                    val namaMahasiswa = document.getString("nama")
                    val password = document.getString("password")
                    mahasiswa = Mahasiswa(nimMahasiswa!!, namaMahasiswa!!, password!!)
                }
            }
        } catch (exception: Exception) {
            Log.e("Get Data Mahasiswa: $exception", "True")
        }
        return mahasiswa
    }

    suspend fun tambahKontak(nama: String, nomor: String): String {
        var errorMessage = ""
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val firebaseFirestore = FirebaseFirestore.getInstance()
        val nim = firebaseAuthentication.currentUser!!.email!!.substringBefore("@")
        val kontakCollection = firebaseFirestore.collection("KontakDarurat")
        val jumlahKontak = kontakCollection.whereEqualTo("nim", nim).get().await().size()

        try {
            if (jumlahKontak < 5) {
                if (!kontakCollection.whereEqualTo("nim", nim).whereEqualTo("nomor", nomor).get().await().isEmpty) {
                    errorMessage = "Nomor telah ditambahkan sebelumnya."
                } else {
                    val kontakBaru = hashMapOf(
                        "nim" to nim,
                        "nomor" to nomor,
                        "nama" to nama
                    )
                    kontakCollection.add(kontakBaru).await()
                }
            } else {
                errorMessage = "Hanya dapat menambahkan maksimal 5 kontak darurat."
            }
        } catch (e: Exception) {
            errorMessage = "Gagal menambahkan kontak darurat"
        }
        return errorMessage
    }

    suspend fun deleteKontak(nomor: String): Boolean {
        var isError = false
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val firebaseFirestore = FirebaseFirestore.getInstance()
        val kontakCollection = firebaseFirestore.collection("KontakDarurat")

        try {
            val nim = firebaseAuthentication.currentUser!!.email!!.substringBefore("@")
            val querySnapshot = kontakCollection
                .whereEqualTo("nim", nim)
                .whereEqualTo("nomor", nomor)
                .get()
                .await()

            if (!querySnapshot.isEmpty) {
                for (document in querySnapshot) {
                    document.reference.delete().await()
                }
            } else {
                isError = true
            }
        } catch (exception: Exception) {
            isError = true
        }

        return isError
    }

    suspend fun getKontakDarurat(): List<KontakDarurat> {
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val firebaseFirestore = FirebaseFirestore.getInstance()
        val kontakCollection = firebaseFirestore.collection("KontakDarurat")
        val kontakDarurat = mutableListOf<KontakDarurat>()
        val nim = firebaseAuthentication.currentUser!!.email!!.substringBefore("@")

        try {
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
            "Dikirim secara otomatis oleh Tangkis: Tangani Kekerasan Seksual: \n\n\nAnda menerima pesan ini karena nomor Anda tercantum sebagai kontak darurat ${mahasiswa?.nama}. \n\n ${mahasiswa?.nama!!.split(" ")[0]} menggunakan fitur pesan darurat di aplikasi Tangkis dan memerlukan bantuan. \n\n ${mahasiswa.nama.split(" ")[0]} membagikan lokasi dengan Anda: https://www.google.com/maps/search/?api=1&query=${lokasi.latitude}%2C${lokasi.longitude} \n\n\nHubungi ${mahasiswa.nama.split(" ")[0]} langsung untuk mendapat pembaruan."
        val parts = smsManager.divideMessage(message)
        daftarKontakDarurat.forEach {
            smsManager.sendMultipartTextMessage(
                it.getNomor(),
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
