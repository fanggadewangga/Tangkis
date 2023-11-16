package com.college.tangkis.feature.contact

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.provider.ContactsContract
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.remote.model.request.contact.ContactRequest
import com.college.tangkis.data.source.remote.model.response.contact.ContactResponse
import com.college.tangkis.data.repository.contact.ContactRepository
import com.college.tangkis.domain.model.DeviceContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private val contactRepository: ContactRepository) :
    ViewModel() {
    val isEmpty = mutableStateOf(false)
    val showDialog = mutableStateOf(false)
    val deleteContactId = mutableStateOf("")
    val deleteContactName = mutableStateOf("")
    val isPermissionGranted = mutableStateOf(false)

    @OptIn(ExperimentalMaterialApi::class)
    val sheetState = mutableStateOf(ModalBottomSheetValue.Hidden)
    var copyOfDeviceContact = mutableStateListOf<DeviceContact>()
    var originalDeviceContacts = mutableStateListOf<DeviceContact>()
    val listOfSelectedContact = mutableStateListOf<DeviceContact>()
    val query = mutableStateOf("")

    private val _getContactState =
        MutableStateFlow<Resource<List<com.college.tangkis.data.source.remote.model.response.contact.ContactResponse>>>(Resource.Loading())
    val getContactState = _getContactState.asStateFlow()

    private val _deleteContactState = MutableStateFlow<Resource<String>>(Resource.Empty())
    val deleteContactState = _deleteContactState.asStateFlow()

    private val _addContactState = MutableStateFlow<Resource<String>>(Resource.Empty())
    val addContactState = _addContactState.asStateFlow()

    fun addContacts() {
        viewModelScope.launch {
            listOfSelectedContact.forEach { contact ->
                val body =
                    com.college.tangkis.data.source.remote.model.request.contact.ContactRequest(
                        name = contact.name,
                        number = contact.number
                    )
                contactRepository.addContact(body).collect {
                    _addContactState.value = it
                }
            }
        }
    }

    fun getContacts() {
        viewModelScope.launch {
            contactRepository.getContacts().collect {
                _getContactState.value = it
            }
        }
    }

    fun deleteContact() {
        viewModelScope.launch {
            contactRepository.deleteContact(deleteContactId.value).collect {
                _deleteContactState.value = it
            }
        }
    }

    @SuppressLint("Recycle", "Range")
    fun loadDeviceContacts(context: Context) {
        val resolver: ContentResolver = context.contentResolver
        val cursor = resolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ),
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )
        try {
            while (cursor!!.moveToNext()) {
                val contactName =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val contactNumber =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val contact = DeviceContact(contactName, contactNumber)
                originalDeviceContacts.add(contact)
            }
            copyOfDeviceContact.addAll(originalDeviceContacts)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun resetDeviceContacts() {
        copyOfDeviceContact.addAll(originalDeviceContacts)
    }

    init {
        getContacts()
    }
}