package com.college.tangkis_rpl.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.college.tangkis_rpl.adapter.HomeArtikelInformasiAdapter
import com.college.tangkis_rpl.adapter.HomeKontakDaruratAdapter
import com.college.tangkis_rpl.contact.KontakDaruratActivity
import com.college.tangkis_rpl.contact.KontakDaruratViewModel
import com.college.tangkis_rpl.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var kontakDaruratViewModel: KontakDaruratViewModel
    private lateinit var contactAdapter: HomeKontakDaruratAdapter
    private lateinit var articleAdapter: HomeArtikelInformasiAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setupView()
        getMahasiswa()
        getKontakDarurat()
        getDaftarArtikelInformasi()
        observe()
        return binding.root
    }

    private fun observe() {
        viewModel.artikelInformasiLiveData.observe(this.requireActivity()) { articles ->
            articleAdapter.artikelInformasis = articles
            articleAdapter.notifyDataSetChanged()
        }

        viewModel.contactLiveData.observe(this.requireActivity()) { contacts ->
            contactAdapter.contacts = contacts
            contactAdapter.notifyDataSetChanged()
        }

        viewModel.mahasiswaLiveData.observe(requireActivity()) {
            binding.tvHeader.text = "Selamat Datang ${it.name}"
        }
    }
    private fun getDaftarArtikelInformasi() {
        viewModel.getArticle()
    }
    private fun getKontakDarurat() {
        viewModel.getContacts()
    }
    private fun getMahasiswa() {
        viewModel.getUserData()
    }
    private fun setupView() {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this.requireActivity())[HomeViewModel::class.java]
        kontakDaruratViewModel = ViewModelProvider(this.requireActivity())[KontakDaruratViewModel::class.java]
        contactAdapter = HomeKontakDaruratAdapter()
        articleAdapter = HomeArtikelInformasiAdapter()

        binding.tvSeeAllContact.setOnClickListener {
            showDaftarKontak()
        }

        binding.rvArticle.apply {
            adapter = articleAdapter
            layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvContact.apply {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }
    private fun showDaftarKontak() {
        val intent = Intent(this.context, KontakDaruratActivity::class.java)
        startActivity(intent)
        kontakDaruratViewModel.getKontakDarurat()
    }
}