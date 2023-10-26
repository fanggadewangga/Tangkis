package com.college.tangkis.feature.home

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val isPermissionGranted = mutableStateOf(false)
    val userLatState = mutableDoubleStateOf(0.0)
    val userLonState = mutableDoubleStateOf(0.0)
    val userAddress = mutableStateOf("")

    fun getAddressFromCoordinate(context: Context) {
        viewModelScope.launch {
            val geoCoder = Geocoder(context)
            val address = geoCoder.getFromLocation(userLatState.value, userLonState.value, 1)
            userAddress.value = address?.get(0)?.getAddressLine(0).toString()
        }
    }
}