package com.college.tangkis_rpl.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Firebase {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore = FirebaseFirestore.getInstance()
    fun getFirebaseAuth(): FirebaseAuth {
        return firebaseAuth
    }
    fun getFirebaseFirestore(): FirebaseFirestore {
        return firebaseFirestore
    }
}