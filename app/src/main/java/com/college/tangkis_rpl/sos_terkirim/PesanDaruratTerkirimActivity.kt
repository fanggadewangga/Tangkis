package com.college.tangkis_rpl.sos_terkirim

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.college.tangkis_rpl.databinding.ActivityPesanDaruratTerkirimBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class PesanDaruratTerkirimActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPesanDaruratTerkirimBinding
    private lateinit var viewModel: PesanDaruratTerkirimViewModel
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesanDaruratTerkirimBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[PesanDaruratTerkirimViewModel::class.java]
        setContentView(binding.root)
        /*val callback = OnMapReadyCallback { googleMap ->
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            map = googleMap
            map.apply {
                animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(0.0, 0.0), 17f))
            }
        }*/
        binding.btnUltksp.setOnClickListener {
            callULTKSP()
        }
    }

    private fun callULTKSP() {
        viewModel.callULKTSP(this)
    }
}