package com.college.tangkis_rpl.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.model.Article
import com.college.tangkis_rpl.model.EmergencyContact
import com.college.tangkis_rpl.model.User
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> = _userLiveData

    private val _articleLiveData = MutableLiveData<List<Article>>()
    val articleLiveData: LiveData<List<Article>> = _articleLiveData

    private val _contactLiveData = MutableLiveData<List<EmergencyContact>>()
    val contactLiveData: LiveData<List<EmergencyContact>> = _contactLiveData

    private val ktorHttpClient = HttpClient(Android) { install(JsonFeature) }

    fun getArticle() {
        val articleEntity = Article()
        viewModelScope.launch {
            _articleLiveData.postValue(articleEntity.getArticle(ktorHttpClient))
        }
    }

    fun getContacts() {
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

    fun getUserData() {
        val userEntity = User()
        viewModelScope.launch {
            _userLiveData.postValue(
                userEntity.getUserDetail(
                    "215150200111033",
                    client = ktorHttpClient
                )
            )
        }
    }
}