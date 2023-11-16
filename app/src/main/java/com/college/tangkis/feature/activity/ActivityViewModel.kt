package com.college.tangkis.feature.activity

import androidx.compose.foundation.gestures.DraggableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.repository.activity.ActivityRepository
import com.college.tangkis.domain.model.activity.Activity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(private val activityRepository: ActivityRepository) : ViewModel() {
    private val _tabIndex: MutableLiveData<Int> = MutableLiveData(0)
    val tabIndex: LiveData<Int> = _tabIndex
    val tabs = listOf("Dalam Proses", "Riwayat")

    private var isSwipeToTheLeft: Boolean = false
    private val draggableState = DraggableState { delta ->
        isSwipeToTheLeft= delta > 0
    }

    private val _dragState = MutableLiveData<DraggableState>(draggableState)
    val dragState: LiveData<DraggableState> = _dragState

    private val _inProgressActivityState = MutableStateFlow<Resource<List<Activity>>>(Resource.Empty())
    val inProgressActivityState = _inProgressActivityState.asStateFlow()

    private val _historyActivityState = MutableStateFlow<Resource<List<Activity>>>(Resource.Empty())
    val historyActivityState = _historyActivityState.asStateFlow()

    fun updateTabIndexBasedOnSwipe() {
        _tabIndex.value = when (isSwipeToTheLeft) {
            true -> Math.floorMod(_tabIndex.value!!.plus(1), tabs.size)
            false -> Math.floorMod(_tabIndex.value!!.minus(1), tabs.size)
        }
    }

    fun updateTabIndex(i: Int) {
        _tabIndex.value = i
    }

    fun getInProgressActivity() {
        viewModelScope.launch {
            activityRepository.getInProgressActivity().collect {
                _inProgressActivityState.value = it
            }
        }
    }

    fun getHistoryActivity() {
        viewModelScope.launch {
            activityRepository.getHistoryActivity().collect {
                _historyActivityState.value = it
            }
        }
    }
}