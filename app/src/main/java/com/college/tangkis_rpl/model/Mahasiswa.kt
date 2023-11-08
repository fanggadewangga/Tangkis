package com.college.tangkis_rpl.model

import com.college.tangkis_rpl.firebase.Firebase

data class Mahasiswa(
    val nim: String = "",
    val nama: String = "",
    val password: String = "",
) {
    fun getDataMahasiswa(nim: String): Mahasiswa? {
        var mahasiswa: Mahasiswa? = null
        val firebase = Firebase()
        val firebaseFirestore = firebase.firebaseFirestore
        firebaseFirestore.collection("Mahasiswa").whereEqualTo("nim", nim).get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    for (document in querySnapshot.documents) {
                        val nimMahasiswa = document.getString("nim")
                        val namaMahasiswa = document.getString("nama")
                        val password = document.getString("password")
                        mahasiswa = Mahasiswa(nimMahasiswa!!, namaMahasiswa!!, password!!)
                    }
                }
            }
            .addOnFailureListener { exception ->
                println("Error: $exception")
            }
        return mahasiswa
    }

    fun tambahKontak(kontak: KontakDarurat, nim: String): String? {
        var errorMessage: String? = ""
        val jumlahKontakDarurat = getJumlahKontakDarurat(nim)

        if (jumlahKontakDarurat < 5) {
            val firebase = Firebase()
            val firebaseFirestore = firebase.firebaseFirestore
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

    private fun getJumlahKontakDarurat(nim: String): Int {
        val firebase = Firebase()
        var jumlahKontak = 0
        val firebaseFirestore = firebase.firebaseFirestore
        val kontakCollection = firebaseFirestore.collection("KontakDarurat")
        kontakCollection.whereEqualTo("nim", nim).get()
            .addOnSuccessListener { querySnapshot ->
                jumlahKontak = querySnapshot.size()
            }
        return jumlahKontak
    }

    fun hapusKontak(nomor: String, nim: String): Boolean {
        var isError = false
        val firebase = Firebase()
        val firebaseFirestore = firebase.firebaseFirestore
        firebaseFirestore.collection("KontakDarurat").whereEqualTo("nim", nim)
            .whereEqualTo("nomor", nomor)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    for (document in querySnapshot) {
                        document.reference.delete()
                            .addOnFailureListener {
                                isError = true
                            }
                    }
                } else {
                    isError = true
                }
            }
            .addOnFailureListener {
                isError = true
            }
        return isError
    }

    fun getDaftarKontakDarurat(nim: String): List<KontakDarurat> {
        val firebase = Firebase()
        val firebaseFirestore = firebase.firebaseFirestore
        val kontakCollection = firebaseFirestore.collection("KontakDarurat")
        val daftarKontakDarurat = mutableListOf<KontakDarurat>()

        kontakCollection.whereEqualTo("nim", nim).get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val nomor = document.getString("nomor")
                    val nama = document.getString("nama")
                    if (nomor != null && nama != null) {
                        val kontak = KontakDarurat(nama, nomor)
                        daftarKontakDarurat.add(kontak)
                    }
                }

            }
            .addOnFailureListener { exception ->
                println("Error: $exception")
            }
        return daftarKontakDarurat.toList()
    }
}
