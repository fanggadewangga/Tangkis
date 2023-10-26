package com.college.tangkis.feature.main.utils

import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

fun getCurrentLocation(
    context: Context,
    onMyLocation: (LatLng) -> Unit,
) {
    try {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val currentLocation = LatLng(location.latitude, location.longitude)
                onMyLocation(currentLocation)
            } ?: run {}
        }
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
}