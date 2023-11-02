package com.college.tangkis.feature.main.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primaryContainer

@Composable
fun ServiceItem(
    modifier: Modifier = Modifier,
    serviceName: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.clickable {
            onClick.invoke()
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        color = md_theme_light_primaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .size(40.dp)
            ) {
                AsyncImage(
                    model = icon,
                    contentDescription = "Service icon",
                    modifier = Modifier.size(24.dp)
                )
            }
            AppText(
                text = serviceName,
                textStyle = Typography.titleMedium(),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}