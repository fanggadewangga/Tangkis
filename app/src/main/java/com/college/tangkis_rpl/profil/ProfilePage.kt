package com.college.tangkis_rpl.profil

import android.app.Dialog
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
import com.college.tangkis_rpl.model.Mahasiswa
import com.google.android.material.button.MaterialButton

class ProfilePage : Fragment() {

    private lateinit var binding: FragmentProfilBinding
    private lateinit var viewModel: ProfileControl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = ViewModelProvider(this.requireActivity())[ProfileControl::class.java]
        binding = FragmentProfilBinding.inflate(layoutInflater)
        viewModel.getProfilData(this)

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
            tvTitleName.visibility = View.GONE
            tvName.visibility = View.GONE
            tvTitleNim.visibility = View.GONE
            tvNim.visibility = View.GONE
            ivProfile.visibility = View.GONE
            includeErrorProfile.ivError.visibility = View.VISIBLE
            includeErrorProfile.tvDescFailProfile.visibility = View.VISIBLE
        }
    }

    fun showProfil(profile: Mahasiswa) {
        binding.apply {
            tvName.text = profile.getNama()
            tvNim.text = profile.getNim()
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
            confirm(false, logoutDialog)
            dismissConfirmationBox(logoutDialog)
        }
        btnHapus.setOnClickListener {
            confirm(true, logoutDialog)
        }
        logoutDialog.show()
    }

    private fun confirm(confirm: Boolean, dialog: Dialog) {
        dismissConfirmationBox(dialog)
        if (confirm)
            viewModel.logout(this)
    }

    private fun dismissConfirmationBox(dialog: Dialog) {
        dialog.dismiss()
    }
}