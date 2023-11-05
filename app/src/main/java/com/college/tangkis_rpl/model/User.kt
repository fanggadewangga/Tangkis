package com.college.tangkis_rpl.model

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val nim: String = "",
    val name: String = "",
    val password: String = "",
) {
    suspend fun getContacts(nim: String, client: HttpClient): List<EmergencyContact> {
        var contacts: List<EmergencyContact> = emptyList()
        val response =
            client.get<BaseResponse<List<EmergencyContact>>>("https://tangkis-api.up.railway.app/user/${nim}/contact") {
                headers {
                    append(
                        "Authorization",
                        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJUYW5na2lzLVVzZXIiLCJuYW5vX2lkIjoiMjE1MTUwMjAwMTExMDMzIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwLyIsImV4cCI6MTcyOTk0NzQ4NH0.hGrDN8HQjzdQtJuz57rzfZtliQtFcuizTE0BjBtfmng"
                    )
                }
            }
        if (!response.error)
            contacts = response.data!!
        return contacts
    }

    suspend fun getUserDetail(nim: String, client: HttpClient): User {
        val response =
            client.get<BaseResponse<User>>("https://tangkis-api.up.railway.app/rpl/user/${nim}") {
                headers {
                    append(
                        "Authorization",
                        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJUYW5na2lzLVVzZXIiLCJuYW5vX2lkIjoiMjE1MTUwMjAwMTExMDMzIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwLyIsImV4cCI6MTcyOTk0NzQ4NH0.hGrDN8HQjzdQtJuz57rzfZtliQtFcuizTE0BjBtfmng"
                    )
                }
            }
        Log.d("NAME", response.data!!.name)
        return response.data!!
    }
}
