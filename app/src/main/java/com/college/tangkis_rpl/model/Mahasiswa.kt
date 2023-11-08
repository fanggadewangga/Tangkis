package com.college.tangkis_rpl.model

import android.util.Log
import com.college.tangkis_rpl.firebase.Firebase
import kotlinx.coroutines.tasks.await

data class Mahasiswa(
    val nim: String = "",
    val nama: String = "",
    val password: String = "",
) {
    suspend fun getDataMahasiswa(): Mahasiswa? {
        Log.d("Get Data Mahasiswa", "Masuk")
        var mahasiswa: Mahasiswa? = null
        val firebase = Firebase()
        val firebaseAuthentication = firebase.firebaseAuth
        val firebaseFirestore = firebase.firebaseFirestore
        val nim = firebaseAuthentication.currentUser!!.email!!.substringBefore("@")

        try {
            val querySnapshot = firebaseFirestore.collection("Mahasiswa").whereEqualTo("nim", nim).get().await()
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
        val firebase = Firebase()
        val firebaseAuthentication = firebase.firebaseAuth
        val firebaseFirestore = firebase.firebaseFirestore
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

    suspend fun hapusKontak(nomor: String, nim: String): Boolean {
        var isError = false
        val firebase = Firebase()
        val firebaseFirestore = firebase.firebaseFirestore

        try {
            val querySnapshot = firebaseFirestore.collection("KontakDarurat")
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
            println("Error: $exception")
        }

        return isError
    }

    suspend fun getKontakDarurat(): List<KontakDarurat> {
        Log.d("GET DAFTAR KONTAK", "MASUK")
        val firebase = Firebase()
        val firebaseFirestore = firebase.firebaseFirestore
        val firebaseAuthentication = firebase.firebaseAuth
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
}
