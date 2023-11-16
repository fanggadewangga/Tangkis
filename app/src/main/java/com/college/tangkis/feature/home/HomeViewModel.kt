package com.college.tangkis.feature.home

import android.content.Context
import android.location.Geocoder
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.repository.article.ArticleRepository
import com.college.tangkis.data.repository.contact.ContactRepository
import com.college.tangkis.data.repository.user.UserRepository
import com.college.tangkis.data.source.remote.model.response.article.ArticleListResponse
import com.college.tangkis.data.source.remote.model.response.contact.ContactResponse
import com.college.tangkis.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val contactRepository: ContactRepository,
    private val articleRepository: ArticleRepository,
) : ViewModel() {
    val isPermissionGranted = mutableStateOf(false)
    val userLatState = mutableDoubleStateOf(0.0)
    val userLonState = mutableDoubleStateOf(0.0)
    val userAddress = mutableStateOf("")

    private val _userState = MutableStateFlow<Resource<User>>(Resource.Loading())
    val userState = _userState.asStateFlow()

    private val _contactState = MutableStateFlow<Resource<List<ContactResponse>>>(Resource.Loading())
    val contactState = _contactState.asStateFlow()

    private val _articleState = MutableStateFlow<Resource<List<ArticleListResponse>>>(Resource.Loading())
    val articleState = _articleState.asStateFlow()

    fun getAddressFromCoordinate(context: Context) {
        viewModelScope.launch {
            val geoCoder = Geocoder(context)
            val address = geoCoder.getFromLocation(userLatState.value, userLonState.value, 1)
            userAddress.value = address?.get(0)?.getAddressLine(0).toString()
        }
    }

    private fun getUserDetail() {
        viewModelScope.launch {
            userRepository.getUserDetail().collect {
                _userState.value = it
            }
        }
    }

    fun getContacts() {
        viewModelScope.launch {
            contactRepository.getContacts().collect {
                _contactState.value = it
            }
        }
    }

    private fun getArticles() {
        viewModelScope.launch {
            articleRepository.getArticles().collect {
                _articleState.value = it
            }
        }
    }

    init {
        getUserDetail()
        getContacts()
        getArticles()
    }
}