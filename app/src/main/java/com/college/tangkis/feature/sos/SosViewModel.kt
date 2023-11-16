package com.college.tangkis.feature.sos

import android.content.Context
import android.location.Geocoder
import android.telephony.SmsManager
import android.util.Log
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.repository.contact.ContactRepository
import com.college.tangkis.domain.model.contact.EmergencyContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("DEPRECATION")
@HiltViewModel
class SosViewModel @Inject constructor(private val contactRepository: ContactRepository) : ViewModel() {
    val isSending = mutableStateOf(false)
    val isInRange = mutableStateOf(false)
    val timeLeft = mutableIntStateOf(5)
    val isPermissionGranted = mutableStateOf(false)
    val userLatState = mutableDoubleStateOf(0.0)
    val userLonState = mutableDoubleStateOf(0.0)
    val userAddress = mutableStateOf("")
    val isSMSPermissionGranted = mutableStateOf(false)

    private val _contactState = MutableStateFlow<Resource<List<EmergencyContact>>>(Resource.Loading())
    val contactState = _contactState.asStateFlow()

    fun getAddressFromCoordinate(context: Context) {
        viewModelScope.launch {
            val geoCoder = Geocoder(context)
            val address = geoCoder.getFromLocation(userLatState.doubleValue, userLonState.doubleValue, 1)
            userAddress.value = address?.get(0)?.getAddressLine(0).toString()
        }
    }

    fun resetState() {
        isSending.value = false
        timeLeft.intValue = 5
    }

    private fun getContacts() {
        viewModelScope.launch {
            contactRepository.getContacts().collect {
                _contactState.value = it
            }
        }
    }

    fun sentEmergencyMessage(destinationNumber: String, message: String) {
        val smsManager = SmsManager.getDefault()
        try {
            smsManager.sendTextMessage(destinationNumber, null, message, null, null)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("Send SMS", e.message.toString())
        }
    }

    init {
        getContacts()
    }
}