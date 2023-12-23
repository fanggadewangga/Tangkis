package com.college.tangkis_rpl.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.college.tangkis_rpl.adapter.ArtikelInformasiAdapter
import com.college.tangkis_rpl.adapter.HomeKontakDaruratAdapter
import com.college.tangkis_rpl.artikel_informasi.ArtikelInformasiControl
import com.college.tangkis_rpl.databinding.FragmentHomeBinding
import com.college.tangkis_rpl.kontak_darurat.KontakDaruratControl
import com.college.tangkis_rpl.kontak_darurat.KontakDaruratPage
import com.college.tangkis_rpl.model.KontakDarurat
import com.college.tangkis_rpl.model.Mahasiswa

class HomePage : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeControl: HomeControl
    private lateinit var kontakDaruratControl: KontakDaruratControl
    private lateinit var artikelInformasiControl: ArtikelInformasiControl
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
        homeControl.getKontakDarurat(this)
        super.onResume()
    }

    private fun getDaftarArtikelInformasi() {
       articleAdapter.artikelInformasis = homeControl.getDaftarArtikelInformasi()
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
        homeControl = ViewModelProvider(this.requireActivity())[HomeControl::class.java]
        kontakDaruratControl = ViewModelProvider(this.requireActivity())[KontakDaruratControl::class.java]
        artikelInformasiControl = ViewModelProvider(this.requireActivity())[ArtikelInformasiControl::class.java]
        contactAdapter = HomeKontakDaruratAdapter()
        articleAdapter = ArtikelInformasiAdapter(onClick = { chooseArtikelInformasi(it) })
        homeControl.getDataMahasiswa(this)
        homeControl.getKontakDarurat(this)

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->

            }
        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.SEND_SMS
            )
        )

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
        val intent = makeIntent(this.requireActivity(), KontakDaruratPage::class.java)
        this.requireActivity().startActivity(intent)
        kontakDaruratControl.getKontakDarurat()
    }

    private fun chooseArtikelInformasi(idArtikel: String) {
        artikelInformasiControl.getArtikelInformasi(idArtikel, this)
    }

    private fun makeIntent(context: android.content.Context, target: Class<KontakDaruratPage>): Intent {
        return Intent(context, target)
    }
}