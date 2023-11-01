package com.college.tangkis.feature.main.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primaryContainer

@Composable
fun AccountConfigurationItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    title: String,
    titleColor: Color = Color.Black,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(32.dp)
                .background(
                    shape = RoundedCornerShape(8.dp),
                    color = md_theme_light_primaryContainer
                )
        ) {
            AsyncImage(
                model = icon,
                contentDescription = "Account Configuration Icon",
                modifier = Modifier
                    .size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        AppText(
            text = title,
            textStyle = Typography.bodyMedium(),
            color = titleColor,
            modifier = Modifier.clickable { onClick.invoke() }
        )
    }
}