package com.college.tangkis.feature.main.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun ActivityItemShimmer(
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        modifier = modifier
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
                    text = "",
                    modifier = Modifier
                        .width(160.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )

                Spacer(modifier = Modifier.height(5.dp))

                AppText(
                    text = "",
                    modifier = Modifier
                        .width(200.dp)
                        .height(16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )
            }

            StatusBox(
                progress = "", modifier = Modifier.clip (RoundedCornerShape(8.dp))
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
            )
        }
    }
}