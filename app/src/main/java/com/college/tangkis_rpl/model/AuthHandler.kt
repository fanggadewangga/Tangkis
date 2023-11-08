package com.college.tangkis_rpl.model

import android.util.Log
import com.college.tangkis_rpl.firebase.Firebase
import kotlinx.coroutines.tasks.await

class AuthHandler {
    fun register(nama: String, nim: String, password: String): String {
        Log.d("Register", "Masuk")
        var errorMessage = ""
        val firebase = Firebase()
        val firebaseAuthentication = firebase.firebaseAuth
        val firebaseFirestore = firebase.firebaseFirestore

        firebaseFirestore.collection("Mahasiswa").document(nim)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    Log.d("Document != null", "NIM telah terdaftar")
                    errorMessage = "NIM telah terdaftar"
                } else {
                    Log.d("Document == null", "Masuk")
                    firebaseAuthentication.createUserWithEmailAndPassword("$nim@tangkis.com", password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user = firebaseAuthentication.currentUser
                                val userMap = hashMapOf(
                                    "nama" to nama,
                                    "nim" to nim,
                                    "password" to password
                                )
                                user?.let {
                                    firebaseFirestore.collection("Mahasiswa").document(nim)
                                        .set(userMap)
                                        .addOnSuccessListener {
                                            Log.d("User data successfully written to Firestore!", "True")
                                        }
                                        .addOnFailureListener { e ->
                                            Log.d("Error writing user data to Firestore: $e", "True")
                                        }
                                }
                            } else {
                                Log.d("User registration failed: ${task.exception?.message}", "True")
                            }
                        }
                }
            }
            .addOnFailureListener { e ->
                Log.d("Error checking NIM in Firestore: $e", "True")
            }
        return errorMessage
    }

    suspend fun getUser(nim: String, password: String): String {
        Log.d("Login", "Masuk")
        val firebase = Firebase()
        val firebaseAuthentication = firebase.firebaseAuth
        val firebaseFirestore = firebase.firebaseFirestore

        try {
            val document = firebaseFirestore.collection("Mahasiswa").document(nim).get().await()

            if (document != null && document.exists()) {
                // NIM is registered, try to sign in
                Log.d("Document != null", "NIM telah terdaftar")
                val authResult = firebaseAuthentication.signInWithEmailAndPassword("$nim@tangkis.com", password).await()

                if (authResult.user != null) {
                    // NIM is registered and password is correct
                    Log.d("User successfully logged in!", "True")
                    return ""
                } else {
                    // NIM is registered but password is incorrect
                    Log.d("Password salah", "True")
                    return "Password salah"
                }
            } else {
                Log.d("NIM tidak terdaftar", "True")
                return "NIM tidak terdaftar"
            }
        } catch (e: Exception) {
            Log.e("Error checking NIM in Firestore: $e", "True")
            return "Error: ${e.message}"
        }
    }

}