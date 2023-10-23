package com.college.tangkis.feature.main.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.theme.Typography

@Composable
fun ErrorLayout(
    @DrawableRes image: Int,
    message: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        AsyncImage(
            model = image,
            contentDescription = "Contactr alert",
            modifier = Modifier.size(280.dp)
        )
        AppText(
            text = message,
            textStyle = Typography.titleLarge(),
            color = Color.Gray
        )
    }
}