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
import com.college.tangkis.domain.model.DeviceContact
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor() : ViewModel() {
    val isEmpty = mutableStateOf(false)
    val showDialog = mutableStateOf(false)
    val deleteContactName = mutableStateOf("")
    val isPermissionGranted = mutableStateOf(false)
    @OptIn(ExperimentalMaterialApi::class)
    val sheetState = mutableStateOf(ModalBottomSheetValue.Hidden)
    val deviceContacts = mutableListOf<DeviceContact>()
    val listOfSelectedContact = mutableStateListOf<DeviceContact>()

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
                val contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val contact = DeviceContact(contactName, contactNumber)
                deviceContacts.add(contact)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}