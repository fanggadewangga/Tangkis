package com.college.tangkis_rpl.model

import android.util.Log
import com.college.tangkis_rpl.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthHandler {
    suspend fun register(nama: String, nim: String, password: String): String {
        Log.d("Register", "Masuk")
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val firebaseFirestore = FirebaseFirestore.getInstance()

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
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val firebaseFirestore = FirebaseFirestore.getInstance()
        val document = firebaseFirestore.collection("Mahasiswa").document(nim).get().await()

        return if (document != null && document.exists()) {
            try {
                val authResult =
                    firebaseAuthentication.signInWithEmailAndPassword("$nim@tangkis.com", password)
                        .await()
                if (authResult.user != null) {
                    ""
                } else
                    "Password salah"
            } catch (e: Exception) {
                "Password salah"
            }
        } else {
            "NIM tidak terdaftar"
        }
    }

    fun checkLoginStatus(): Boolean {
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val currentUser = firebaseAuthentication.currentUser
        var isLogin = false
        if (currentUser != null) {
            isLogin = true
            Log.d("Current User Email", currentUser.email!!.substringBefore("@"))
        }
        return isLogin
    }
}