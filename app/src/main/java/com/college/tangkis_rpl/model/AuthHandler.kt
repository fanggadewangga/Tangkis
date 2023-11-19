package com.college.tangkis_rpl.model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthHandler {
    suspend fun register(nama: String, nim: String, password: String): String {
        var errorMessage = ""
        Log.d("Register", "Masuk")
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val firebaseFirestore = FirebaseFirestore.getInstance()

        // Cek apakah NIM mahasiswa FILKOM dan panjang password
        if (!Regex("""^\d{2}515\d*$""").matches(nim) || password.length < 8) {
            errorMessage = "Data tidak valid!"
            return errorMessage
        }

        try {
            val document = firebaseFirestore.collection("Mahasiswa").document(nim).get().await()

            if (document != null && document.exists()) {
                errorMessage = "NIM telah terdaftar"
                return errorMessage
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
                    firebaseAuthentication.signOut()
                }
                return errorMessage
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return errorMessage
    }

    suspend fun getUser(nim: String, password: String): String {
        var errorMessage = ""
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val firebaseFirestore = FirebaseFirestore.getInstance()

        try {
            val document = firebaseFirestore.collection("Mahasiswa").document(nim).get().await()

            if (document != null && document.exists()) {
                val authResult =
                    firebaseAuthentication.signInWithEmailAndPassword("$nim@tangkis.com", password)
                        .await()
                if (authResult.user == null) {
                    errorMessage = "Password salah"
                    return errorMessage
                }
            } else {
                errorMessage = "NIM tidak terdaftar"
                return errorMessage
            }
        } catch (e: Exception) {
            errorMessage = "Password salah"
            return errorMessage
        }
        return errorMessage
    }

    fun checkLoginStatus(): Boolean {
        val firebaseAuthentication = FirebaseAuth.getInstance()
        val currentUser = firebaseAuthentication.currentUser
        var isLogin = false
        if (currentUser != null) {
            isLogin = true
        }
        return isLogin
    }
}