package com.college.tangkis.feature.activity

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.college.tangkis.feature.main.components.ActivityItem

@Composable
fun HistoryScreen(viewModel: ActivityViewModel) {
    Column(modifier = Modifier
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
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        // DUMMY DATA, NEED LAZY COLUMN IMPLEMENTATION!
        ActivityItem(title = "Konsultasi ULTKSP", progress = "Selesai", updatedAt = "22 Oktober 2023")
    }
}