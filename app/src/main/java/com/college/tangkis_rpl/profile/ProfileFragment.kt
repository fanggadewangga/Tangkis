package com.college.tangkis_rpl.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.college.tangkis_rpl.R
import com.college.tangkis_rpl.databinding.FragmentHomeBinding
import com.college.tangkis_rpl.databinding.FragmentProfileBinding
import com.college.tangkis_rpl.home.HomeViewModel
import com.college.tangkis_rpl.model.Mahasiswa

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this.requireActivity())[ProfileViewModel::class.java]
        viewModel.getProfile(this)
        return binding.root
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
}