package com.college.tangkis_rpl.model

class KontakDarurat(
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
}

