package com.college.tangkis.feature.main.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun EmergencyContactItemShimmer(
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {

            // Left section
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = R.drawable.iv_contact_icon,
                    contentDescription = "Contact icon",
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    AppText(
                        text = "",
                        modifier = Modifier
                            .width(80.dp)
                            .height(16.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .placeholder(
                                visible = true,
                                color = Color.LightGray,
                                highlight = PlaceholderHighlight
                                    .shimmer(highlightColor = Color.White),
                            )
                    )
                    AppText(
                        text = "",
                        modifier = Modifier
                            .width(120.dp)
                            .height(16.dp)
                            .padding(top = 4.dp)
                            .clip(RoundedCornerShape(8.dp))
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
    }
}