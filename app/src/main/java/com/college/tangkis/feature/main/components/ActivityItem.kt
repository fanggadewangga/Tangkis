package com.college.tangkis.feature.main.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.college.tangkis.domain.model.activity.Activity
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.status_diproses_background
import com.college.tangkis.theme.yellow
import com.college.tangkis.theme.status_selesai_background
import com.college.tangkis.theme.status_selesai_text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityItem(
    modifier: Modifier = Modifier,
    activity: Activity,
    navigateToReportDetail: () -> Unit,
    navigateToConsultationDetail: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        modifier = modifier
            .clickable {
                if (activity.activityId.contains("REPORT")) navigateToReportDetail.invoke() else if (activity.activityId.contains(
                        "CONSULTATION"
                    )
                ) navigateToConsultationDetail.invoke()
            }
            .padding(bottom = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                AppText(
                    text = activity.title,
                    textStyle = Typography.titleMedium()
                )

                Spacer(modifier = Modifier.height(5.dp))

                AppText(
                    text = "Terakhir update ${activity.updateDate}",
                    textStyle = Typography.bodySmall(),
                    color = Color.Gray
                )
            }
            StatusBox(progress = activity.progress)
        }
    }
}

@Composable
fun StatusBox(
    modifier: Modifier = Modifier,
    progress: String,
) {
    val backgroundColor: Color =
        if (progress == "Diproses") status_diproses_background else status_selesai_background
    val textColor: Color = if (progress == "Diproses") yellow else status_selesai_text

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
            .width(80.dp)
            .height(28.dp)
    ) {
        AppText(
            text = progress,
            textStyle = Typography.labelStatus(),
            color = textColor,
        )
    }
}