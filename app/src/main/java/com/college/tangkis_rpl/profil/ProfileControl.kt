package com.college.tangkis_rpl.profil

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.login.LoginPage
import com.college.tangkis_rpl.model.Mahasiswa
import kotlinx.coroutines.launch

class ProfileControl : ViewModel() {
    fun getProfilData(profilePage: ProfilePage) {
        viewModelScope.launch {
            var mahasiswa: Mahasiswa? = Mahasiswa()
            mahasiswa = mahasiswa?.getProfilData()
            Log.d("PROFILE VM", mahasiswa.toString())
            if (mahasiswa != null)
                profilePage.showProfil(mahasiswa)
            else
                profilePage.showAlert()
        }
    }

    fun logout(activity: ProfilePage) {
        val mahasiswa = Mahasiswa()
        val intent = makeIntent(activity.requireActivity(), LoginPage::class.java)
        mahasiswa.logout()
        activity.requireActivity().startActivity(intent)
        activity.requireActivity().finish()
    }

    private fun makeIntent(context: android.content.Context, target: Class<LoginPage>): Intent {
        return Intent(context, target)
    }
}