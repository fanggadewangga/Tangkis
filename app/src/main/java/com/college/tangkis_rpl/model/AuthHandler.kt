package com.college.tangkis_rpl.model

import android.util.Log
import com.college.tangkis_rpl.firebase.Firebase
import kotlinx.coroutines.tasks.await

class AuthHandler {
    suspend fun register(nama: String, nim: String, password: String): String {
        Log.d("Register", "Masuk")
        val firebase = Firebase()
        val firebaseAuthentication = firebase.firebaseAuth
        val firebaseFirestore = firebase.firebaseFirestore

        // Cek apakah NIM mahasiswa FILKOM dan panjang password
        if (!Regex("""^\d{2}515\d*$""").matches(nim) || password.length < 8) {
            return "Data tidak valid!"
        }

        try {
            val document = firebaseFirestore.collection("Mahasiswa").document(nim).get().await()

            if (document != null && document.exists()) {
                return "NIM telah terdaftar"
            } else {
                val authResult = firebaseAuthentication.createUserWithEmailAndPassword(
                    "$nim@tangkis.com",
                    password
                ).await()

                if (authResult.user != null) {
                    val user = firebaseAuthentication.currentUser
                    val userMap = hashMapOf(
                        "nama" to nama,
                        "nim" to nim,
                        "password" to password
                    )
                    user?.let {
                        firebaseFirestore.collection("Mahasiswa").document(nim)
                            .set(userMap)
                            .await()
                    }
                    return ""
                } else {
                    return "User registration failed"
                }
            }
        } catch (e: Exception) {
            return "Error during registration"
        }
    }

    suspend fun getUser(nim: String, password: String): String {
        val firebase = Firebase()
        val firebaseAuthentication = firebase.firebaseAuth
        val firebaseFirestore = firebase.firebaseFirestore
        val document = firebaseFirestore.collection("Mahasiswa").document(nim).get().await()

        if (document != null && document.exists()) {
            try {
                val authResult =
                    firebaseAuthentication.signInWithEmailAndPassword("$nim@tangkis.com", password)
                        .await()
                return if (authResult.user != null)
                    ""
                else
                    return "Password salah"
            } catch (e: Exception) {
                return "Password salah"
            }
        } else {
            return "NIM tidak terdaftar"
        }
    }
}