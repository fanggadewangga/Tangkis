package com.college.tangkis_rpl.pesandarurat

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class PesanDaruratViewModel : ViewModel() {
    private var fusedLocationClient: FusedLocationProviderClient? = null


    fun initiatePesanDarurat(activity: PesanDaruratActivity) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, activity) && checkPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                activity
            )
        ) {
            fusedLocationClient!!.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    Log.d("Latitude", location.latitude.toString())
                    Log.d("Longitude", location.longitude.toString())
                    val valid = validateLocation(latitude, longitude)
                    activity.activateButton(valid)
                }
            }
        }
    }

    private fun validateLocation(latitude: Double, longitude: Double): Boolean {
        val mahasiswaLocation = Location("Mahasiswa")
        mahasiswaLocation.latitude = latitude!!
        mahasiswaLocation.longitude = longitude!!
        val filkomLocation = Location("FILKOM")
        filkomLocation.latitude = -7.954056949829905
        filkomLocation.longitude = 112.61448436435153
        val distance = filkomLocation.distanceTo(mahasiswaLocation) / 1000
        return distance <= 5.0
    }

    fun cancelPesanDarurat(activity: PesanDaruratActivity) {
        activity.show()
    }

    fun initializePesanDarurat() {

    }

    private fun checkPermission(permission: String, context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}