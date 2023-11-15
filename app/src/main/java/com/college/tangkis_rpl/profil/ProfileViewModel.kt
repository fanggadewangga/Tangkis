package com.college.tangkis_rpl.profil

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.login.LoginActivity
import com.college.tangkis_rpl.model.Mahasiswa
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    fun getProfile(profileFragment: ProfileFragment) {
        viewModelScope.launch {
            var mahasiswa: Mahasiswa? = Mahasiswa()
            mahasiswa = mahasiswa?.getProfilData()
            if (mahasiswa != null)
                profileFragment.showProfil(mahasiswa)
            else
                profileFragment.showAlert()
        }
    }

    fun logout(profileFragment: ProfileFragment) {
        val mahasiswa = Mahasiswa()
        val intent = Intent(profileFragment.requireActivity(), LoginActivity::class.java)
        mahasiswa.logout()
        profileFragment.requireActivity().startActivity(intent)
        profileFragment.requireActivity().finish()
    }
}