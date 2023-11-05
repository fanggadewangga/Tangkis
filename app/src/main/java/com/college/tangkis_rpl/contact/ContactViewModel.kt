package com.college.tangkis_rpl.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.model.EmergencyContact
import com.college.tangkis_rpl.model.User
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import kotlinx.coroutines.launch

class ContactViewModel: ViewModel() {
    private val _contactLiveData = MutableLiveData<List<EmergencyContact>>()
    val contactLiveData: LiveData<List<EmergencyContact>> = _contactLiveData

    private val ktorHttpClient = HttpClient(Android) { install(JsonFeature) }

    private fun getContacts() {
        val userEntity = User()
        viewModelScope.launch {
            _contactLiveData.postValue(
                userEntity.getContacts(
                    "215150200111033",
                    client = ktorHttpClient
                )
            )
        }
    }

    init {
        getContacts()
    }
}