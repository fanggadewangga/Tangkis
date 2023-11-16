package com.college.tangkis.feature.main.components

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
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun HomeArticleItemShimmer(
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .width(280.dp)
            .height(180.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = null,
                    contentDescription = "Article image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )
            }

            // Description
            AppText(
                text = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(24.dp)
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

@Composable
fun ArticleItemShimmer(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            // Image
            AsyncImage(
                model = null,
                contentDescription = "Article item",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
            )
            Spacer(modifier = Modifier.width(16.dp))


            // Text
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {

                // Title
                AppText(
                    text = "",
                    modifier = Modifier
                        .width(120.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )

                // Description
                AppText(
                    text = "",
                    modifier = Modifier
                        .width(180.dp)
                        .height(18.dp)
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

        // Divider
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            color = Color.LightGray, modifier = Modifier
                .fillMaxWidth()
                .placeholder(
                    visible = true,
                    color = Color.LightGray,
                    highlight = PlaceholderHighlight
                        .shimmer(highlightColor = Color.White),
                )
        )
    }
}