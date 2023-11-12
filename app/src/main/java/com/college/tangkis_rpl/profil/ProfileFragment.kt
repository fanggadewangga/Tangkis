package com.college.tangkis_rpl.profil

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.college.tangkis_rpl.R
import com.college.tangkis_rpl.databinding.FragmentProfilBinding
import com.college.tangkis_rpl.login.LoginActivity
import com.college.tangkis_rpl.model.Mahasiswa
import com.google.android.material.button.MaterialButton

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfilBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = ViewModelProvider(this.requireActivity())[ProfileViewModel::class.java]
        binding = FragmentProfilBinding.inflate(layoutInflater)
        viewModel.getProfile(this)

        // Logout
        binding.tvLogout.setOnClickListener {
            logout()
        }
        return binding.root
    }

    private fun logout() {
        showConfirmationBox()
    }

    fun showAlert() {
        binding.apply {
            tvName.visibility = View.GONE
            tvNim.visibility = View.GONE
            includeErrorProfile.ivError.visibility = View.VISIBLE
            includeErrorProfile.tvDescFailProfile.visibility = View.VISIBLE
        }
    }

    fun showProfil(profile: Mahasiswa) {
        binding.apply {
            tvName.text = profile.nama
            tvNim.text = profile.nim
        }
    }

    private fun showConfirmationBox() {
        val logoutDialog = Dialog(this.requireContext())
        logoutDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        logoutDialog.setCancelable(false)
        logoutDialog.setContentView(R.layout.dialog_logout)
        logoutDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btnBatal: MaterialButton = logoutDialog.findViewById(R.id.btn_batal)
        val btnHapus: MaterialButton = logoutDialog.findViewById(R.id.btn_hapus)
        btnBatal.setOnClickListener {
            dismissConfirmationBox(logoutDialog)
        }
        btnHapus.setOnClickListener {
            confirm(logoutDialog)
        }
        logoutDialog.show()
    }

    private fun confirm(dialog: Dialog) {
        dialog.dismiss()
        viewModel.logout(this)
    }

    private fun dismissConfirmationBox(dialog: Dialog) {
        dialog.dismiss()
    }

    fun showLoginPage() {
        val intent = Intent(this.requireActivity(), LoginActivity::class.java)
        this.requireActivity().startActivity(intent)
        this.requireActivity().finish()
    }
}