package com.college.tangkis.feature.activity

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.college.tangkis.R
import com.college.tangkis.data.Resource
import com.college.tangkis.feature.main.components.ActivityItem
import com.college.tangkis.feature.main.components.ActivityItemShimmer
import com.college.tangkis.feature.main.components.ErrorLayout
import com.college.tangkis.feature.main.route.Screen

@Composable
fun InProgressScreen(
    viewModel: ActivityViewModel,
    navController: NavController,
) {
    val inProgressActivityState = viewModel.inProgressActivityState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.getInProgressActivity()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .draggable(
                state = viewModel.dragState.value!!,
                orientation = Orientation.Horizontal,
                onDragStopped = {
                    viewModel.updateTabIndexBasedOnSwipe()
                })
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 8.dp
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        when (inProgressActivityState.value) {
            is Resource.Loading -> {
                LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                    items(5) {
                        ActivityItemShimmer()
                    }
                }
            }

            is Resource.Success -> {
                LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                    if (inProgressActivityState.value is Resource.Success && !inProgressActivityState.value.data.isNullOrEmpty())
                        items(inProgressActivityState.value.data!!) {
                            ActivityItem(
                                activity = it,
                                navigateToConsultationDetail = {
                                    navController.navigate(
                                        Screen.ConsultationDetail.route.replace(
                                            oldValue = "{consultationId}",
                                            newValue = it.activityId
                                        )
                                    )
                                },
                                navigateToReportDetail = {
                                    navController.navigate(
                                        Screen.ReportDetail.route.replace(
                                            oldValue = "{reportId}",
                                            newValue = it.activityId
                                        )
                                    )
                                }
                            )
                        }
                }
            }

            is Resource.Error -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    ErrorLayout(
                        image = R.drawable.iv_error,
                        message = "Aktivitas gagal ditampilkan"
                    )
                }
            }

            else -> {}
        }
    }
}