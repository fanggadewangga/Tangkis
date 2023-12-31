package com.college.tangkis.feature.main.components

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
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.college.tangkis.domain.model.article.ArticleList
import com.college.tangkis.theme.Typography

@Composable
fun HomeArticleItem(
    modifier: Modifier = Modifier,
    article: ArticleList,
    onClick: (String) -> Unit,
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .width(280.dp)
            .height(180.dp)
            .clickable { onClick(article.articleId) }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = article.imageUrl,
                    contentDescription = "Article image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                                startY = 120.toFloat() / 3,
                                endY = 120.toFloat()
                            )
                        )
                )

                // Title
                AppText(
                    text = article.title,
                    maxLine = 2,
                    overflow = TextOverflow.Ellipsis,
                    textStyle = Typography.titleSmall(),
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 10.dp)
                        .align(Alignment.BottomStart)
                )
            }

            // Description
            AppText(
                text = article.content,
                maxLine = 2,
                overflow = TextOverflow.Ellipsis,
                textStyle = Typography.bodySmall(),
                color = Color.Gray,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    article: ArticleList,
    onClick: (String) -> Unit,
) {
    Column(modifier = modifier.clickable { onClick(article.articleId) }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            // Image
            AsyncImage(
                model = article.imageUrl,
                contentDescription = "Article item",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(80.dp)
                    .height(88.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))


            // Text
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {

                // Title
                AppText(
                    text = article.title,
                    textStyle = Typography.titleSmall(),
                    maxLine = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                // Description
                AppText(
                    text = article.content,
                    maxLine = 2,
                    overflow = TextOverflow.Ellipsis,
                    textStyle = Typography.bodyMedium(),
                    color = Color.Gray
                )
            }
        }

        // Divider
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth())
    }
}