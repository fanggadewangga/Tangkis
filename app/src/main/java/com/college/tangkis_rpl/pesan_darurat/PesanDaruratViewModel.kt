package com.college.tangkis_rpl.pesan_darurat

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.informasi_pesan_darurat.InformasiPesanDaruratActivity
import com.college.tangkis_rpl.model.Mahasiswa
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class PesanDaruratViewModel : ViewModel() {
    private var fusedLocationClient: FusedLocationProviderClient? = null


    fun initiatePesanDaruratButton(activity: PesanDaruratActivity) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, activity) && checkPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                activity
            )
        ) {
            Log.d("LOCATION", "DIIZINKAN")
            fusedLocationClient!!.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val valid = validateLocation(latitude, longitude)
                    Log.d("LATITUDE", latitude.toString())
                    Log.d("LONGITUDE", longitude.toString())
                    activity.activateButton(valid)
                }
            }
        }
    }

    private fun validateLocation(latitude: Double, longitude: Double): Boolean {
        val mahasiswaLocation = Location("Mahasiswa")
        mahasiswaLocation.latitude = latitude
        mahasiswaLocation.longitude = longitude
        val filkomLocation = Location("FILKOM")
        filkomLocation.latitude = -7.954056949829905
        filkomLocation.longitude = 112.61448436435153
        val distance = filkomLocation.distanceTo(mahasiswaLocation) / 1000
        return distance <= 3.0
    }

    fun cancelPesanDarurat(activity: PesanDaruratActivity) {
        activity.show()
    }

    fun initiatePesanDarurat(activity: PesanDaruratActivity) {
        viewModelScope.launch {
            val mahasiswa = Mahasiswa()
            var latitude = 0.0
            var longitude = 0.0
            val intentKontakDarurat = mutableListOf<String>()
            val intent = Intent(activity, InformasiPesanDaruratActivity::class.java)

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
            if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, activity) && checkPermission(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    activity
                )
            ) {
                fusedLocationClient!!.lastLocation.addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        latitude = location.latitude
                        longitude = location.longitude
                        intent.putExtra("latitude", latitude)
                        intent.putExtra("longitude", longitude)
                    }
                }
            }

            val kontakDarurat = mahasiswa.getKontakDarurat()
            if (kontakDarurat.isNotEmpty()) {
                mahasiswa.sendPesanDarurat(kontakDarurat, LatLng(latitude, longitude))
                intentKontakDarurat.addAll(kontakDarurat.map { it.getNama() })
                intent.putStringArrayListExtra("kontak_darurat", ArrayList(intentKontakDarurat))
                activity.startActivity(intent)
            }
        }
    }

    private fun checkPermission(permission: String, context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}