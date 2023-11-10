package com.college.tangkis_rpl.informasi_pesan_darurat

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.college.tangkis_rpl.adapter.KontakDaruratTerkirimAdapter
import com.college.tangkis_rpl.databinding.ActivityPesanDaruratTerkirimBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class InformasiPesanDaruratActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPesanDaruratTerkirimBinding
    private lateinit var viewModel: InformasiPesanDaruratViewModel
    private lateinit var map: GoogleMap
    private lateinit var kontakDaruratAdapter: KontakDaruratTerkirimAdapter
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private val boundsBuilder = LatLngBounds.Builder()
    private lateinit var location: LatLng

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesanDaruratTerkirimBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[InformasiPesanDaruratViewModel::class.java]
        latitude = intent.getDoubleExtra("latitude", 0.0)
        longitude = intent.getDoubleExtra("longitude", 0.0)
        location = LatLng(latitude, longitude)
        kontakDaruratAdapter = KontakDaruratTerkirimAdapter()
        val kontakDarurat = intent.getStringArrayListExtra("kontak_darurat")

        if (kontakDarurat != null) {
            kontakDaruratAdapter.contactNames = kontakDarurat
            kontakDaruratAdapter.notifyDataSetChanged()
        }

        binding.rvKontakDarurat.apply {
            adapter = kontakDaruratAdapter
            layoutManager =
                LinearLayoutManager(
                    this@InformasiPesanDaruratActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
        }
        binding.btnUltksp.setOnClickListener {
            callULTKSP()
        }


        val callback = OnMapReadyCallback { googleMap ->
            boundsBuilder.include(location)
            map = googleMap
            map.addMarker(MarkerOptions().position(location).title("Lokasi Pesan Darurat"))
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 17f))
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 50))
        }

        val supportMapFragment = supportFragmentManager.findFragmentById(binding.maps.id) as? SupportMapFragment
        supportMapFragment?.getMapAsync(callback)
        setContentView(binding.root)
    }

    private fun callULTKSP() {
        viewModel.callULKTSP(this)
    }
}