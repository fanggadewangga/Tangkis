package com.college.tangkis_rpl.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.college.tangkis_rpl.adapter.ArtikelInformasiAdapter
import com.college.tangkis_rpl.adapter.HomeKontakDaruratAdapter
import com.college.tangkis_rpl.artikel_informasi.ArtikelInformasiViewModel
import com.college.tangkis_rpl.databinding.FragmentHomeBinding
import com.college.tangkis_rpl.kontak_darurat.KontakDaruratActivity
import com.college.tangkis_rpl.kontak_darurat.KontakDaruratViewModel
import com.college.tangkis_rpl.model.KontakDarurat
import com.college.tangkis_rpl.model.Mahasiswa

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var kontakDaruratViewModel: KontakDaruratViewModel
    private lateinit var artikelInformasiViewModel: ArtikelInformasiViewModel
    private lateinit var contactAdapter: HomeKontakDaruratAdapter
    private lateinit var articleAdapter: ArtikelInformasiAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setupView()
        getDaftarArtikelInformasi()
        return binding.root
    }

    override fun onResume() {
        viewModel.getKontakDarurat(this)
        super.onResume()
    }

    private fun getDaftarArtikelInformasi() {
       articleAdapter.artikelInformasis = viewModel.getDaftarArtikelInformasi()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun showKontakDarurat(kontakDarurat: List<KontakDarurat>) {
        contactAdapter.contacts = kontakDarurat
        contactAdapter.notifyDataSetChanged()
    }

    fun showDataMahasiswa(mahasiswa: Mahasiswa) {
        binding.apply {
            tvHeader.text = "Selamat datang ${mahasiswa?.getNama()}"
        }
    }

    private fun setupView() {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this.requireActivity())[HomeViewModel::class.java]
        kontakDaruratViewModel = ViewModelProvider(this.requireActivity())[KontakDaruratViewModel::class.java]
        artikelInformasiViewModel = ViewModelProvider(this.requireActivity())[ArtikelInformasiViewModel::class.java]
        contactAdapter = HomeKontakDaruratAdapter()
        articleAdapter = ArtikelInformasiAdapter(onClick = { chooseArtikelInformasi(it) })
        viewModel.getDataMahasiswa(this)
        viewModel.getKontakDarurat(this)

        binding.tvSeeAllContact.setOnClickListener {
            showDaftarKontak()
        }

        binding.rvArticle.apply {
            adapter = articleAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvContact.apply {
            adapter = contactAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun showDaftarKontak() {
        val intent = Intent(this.requireActivity(), KontakDaruratActivity::class.java)
        this.requireActivity().startActivity(intent)
        kontakDaruratViewModel.getKontakDarurat()
    }

    private fun chooseArtikelInformasi(idArtikel: String) {
        artikelInformasiViewModel.getArtikelInformasi(idArtikel, this)
    }
}