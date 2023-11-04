package com.college.tangkis.feature.main.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.status_diproses_background
import com.college.tangkis.theme.status_diproses_text
import com.college.tangkis.theme.status_selesai_background
import com.college.tangkis.theme.status_selesai_text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityItem(
    modifier: Modifier = Modifier,
    title: String,
    progress: String,
    updatedAt: String,
    onClick: () -> Unit = {}
){
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ){
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                AppText(
                    text = title,
                    textStyle = Typography.titleMedium()
                )

                Spacer(modifier = Modifier.height(5.dp))

                AppText(
                    text = "Terakhir update $updatedAt",
                    textStyle = Typography.bodySmall(),
                    color = Color.Gray
                )
            }
            StatusBox(progress = progress)
        }
    }
}

@Composable
fun StatusBox(
    progress: String
) {
    val backgroundColor: Color = if (progress == "Diproses") status_diproses_background else status_selesai_background
    val textColor: Color = if (progress == "Diproses") status_diproses_text else status_selesai_text

    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
            .width(80.dp)
            .height(28.dp)
    ){
        AppText(
            text = progress,
            textStyle = Typography.labelStatus(),
            color = textColor,
        )
    }
}

@Preview
@Composable
fun ActivityItemPreview() {
    ActivityItem(title = "Pelaporan ULTKSP", updatedAt = "22 Oktober 2023", progress = "Selesai")
}