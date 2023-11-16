package com.college.tangkis.feature.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.college.tangkis.domain.model.Faq
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primaryContainer
import com.college.tangkis.theme.md_theme_light_secondary
import com.college.tangkis.theme.md_theme_light_secondaryContainer

@Composable
fun FaqItem(
    modifier: Modifier = Modifier,
    faq: Faq
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .background(
                color = md_theme_light_secondaryContainer,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = md_theme_light_primaryContainer),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { expanded = !expanded },
                    indication = rememberRipple(color = Color.White),
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppText(
                    text = faq.question,
                    textStyle = Typography.labelLarge(),
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(280.dp)
                )
                Image(
                    imageVector = if (expanded) Icons.Default.ArrowDropDown else Icons.Default.ArrowRight,
                    contentDescription = "Arrow",
                    colorFilter = ColorFilter.tint(
                        md_theme_light_secondary
                    ),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        if (expanded) {
            AppText(
                text = faq.answer,
                textStyle = Typography.bodyMedium(),
                modifier = Modifier.padding(16.dp)

            )
        }
    }
}