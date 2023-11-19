package com.college.tangkis_rpl.model

import android.telephony.SmsManager
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@Suppress("DEPRECATION")
class Mahasiswa(
    private var nim: String = "",
    private var nama: String = "",
    private var password: String = "",
) {

    fun getNim(): String {
        return nim
    }

    fun setNim(newNim: String) {
        nim = newNim
    }

    fun getNama(): String {
        return nama
    }

    fun setNama(newNama: String) {
        nama = newNama
    }

    fun getPassword(): String {
        return password
    }

    fun setPassword(newPassword: String) {
        password = newPassword
    }

    suspend fun getProfilData(): Mahasiswa? {
        var mahasiswa: Mahasiswa? = null
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val firebaseFirestore = FirebaseFirestore.getInstance()
        val mahasiswaCollection = firebaseFirestore.collection("Mahasiswa")
        val nim = firebaseAuthentication.currentUser!!.email!!.substringBefore("@")

        try {
            val querySnapshot =
                mahasiswaCollection.whereEqualTo("nim", nim).get().await()
            if (!querySnapshot.isEmpty) {
                for (document in querySnapshot.documents) {
                    val nimMahasiswa = document.getString("nim")
                    val namaMahasiswa = document.getString("nama")
                    val password = document.getString("password")
                    mahasiswa = Mahasiswa(nimMahasiswa!!, namaMahasiswa!!, password!!)
                }
                return mahasiswa
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
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
                    return errorMessage
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
                return errorMessage
            }
        } catch (e: Exception) {
            errorMessage = "Gagal menambahkan kontak darurat"
            return errorMessage
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
                return isError
            }
        } catch (exception: Exception) {
            isError = true
            return isError
        }

        return isError
    }

    suspend fun getKontakDarurat(): List<KontakDarurat> {
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val firebaseFirestore = FirebaseFirestore.getInstance()
        val kontakCollection = firebaseFirestore.collection("KontakDarurat")
        val kontakDarurat = mutableListOf<KontakDarurat>()
        val nim = firebaseAuthentication.currentUser!!.email!!.substringBefore("@")

        val querySnapshot = kontakCollection.whereEqualTo("nim", nim).get().await()
        for (document in querySnapshot) {
            val nomor = document.getString("nomor")
            val nama = document.getString("nama")
            if (nomor != null && nama != null) {
                val kontak = KontakDarurat(nama, nomor)
                kontakDarurat.add(kontak)
            }
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
