package com.college.tangkis_rpl.model

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.provider.ContactsContract

class KontakPerangkat(
    private var nama: String = "",
    private var nomor: String = "",
) {

    fun getNama(): String {
        return nama
    }

    fun setNama(newNama: String) {
        nama = newNama
    }

    fun getNomor(): String {
        return nomor
    }

    fun setNomor(newNomor: String) {
        nomor = newNomor
    }

    @SuppressLint("Range", "Recycle")
    fun getDaftarKontakPerangkat(context: Context): List<KontakPerangkat> {
        val daftarKontakPerangkat = mutableListOf<KontakPerangkat>()
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
                val contact = KontakPerangkat(contactName, contactNumber)
                daftarKontakPerangkat.add(contact)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return daftarKontakPerangkat
    }
}
