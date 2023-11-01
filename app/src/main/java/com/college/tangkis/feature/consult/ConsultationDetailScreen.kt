package com.college.tangkis.feature.consult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.modifier.ModifierLocal
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.consulationDetail_containerBackground
import com.college.tangkis.theme.md_theme_dark_onPrimaryContainer
import com.college.tangkis.theme.md_theme_dark_outline
import com.college.tangkis.theme.md_theme_dark_outlineVariant
import com.college.tangkis.theme.md_theme_dark_surfaceVariant
import com.college.tangkis.theme.md_theme_light_onTertiaryContainer
import com.college.tangkis.theme.md_theme_light_primary
import com.college.tangkis.theme.md_theme_light_tertiaryContainer
import com.college.tangkis.theme.status_diproses_background
import com.college.tangkis.theme.status_diproses_text
import com.college.tangkis.theme.status_selesai_background
import com.college.tangkis.theme.status_selesai_text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultationDetailScreen(navController: NavController) {
    val viewModel = hiltViewModel<ConsultationDetailViewModel>()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AppText(
                        text = "Konsultasi ULTKSP",
                        color = Color.White,
                        textStyle = Typography.titleLarge()
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = md_theme_light_primary,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        AsyncImage(
                            model = R.drawable.ic_back,
                            contentDescription = "Back Icon",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    ) {
        val topPadding = it.calculateTopPadding()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topPadding)
                .verticalScroll(rememberScrollState())
        ) {

            // Date & Time
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppText(
                    text = "Konsultasi bersama",
                    textStyle = Typography.bodySmall(),
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                AppText(
                    text = "22 Okt 2023, 16:00",
                    textStyle = Typography.bodySmall(),
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            // Status
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AppText(
                    text = "ULTKSP",
                    textStyle = Typography.headlineSmall(),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .width(80.dp)
                        .height(28.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(status_selesai_background),
                    contentAlignment = Alignment.Center
                ) {
                    AppText(
                        text = "Selesai",
                        textStyle = Typography.labelStatus(),
                        color = status_selesai_text,
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 3.dp)
                    )
                }

            }

            // Divider (Garis)
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            )

            // Gambar & Teks "Hari dan Tanggal"
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            ) {
                Image(
                    imageVector = Icons.Default.Today,
                    contentDescription = "Calendar icon",
                    colorFilter = ColorFilter.tint(Color.Gray),
                    modifier = Modifier.size(24.dp)
                )

                AppText(
                    text = "Hari dan Tanggal",
                    textStyle = Typography.labelLarge(),
                    color = Color.Gray
                )
            }

            // Field "Hari dan Tanggal"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                        .width(380.dp)
                        .height(56.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(consulationDetail_containerBackground),
                    contentAlignment = Alignment.CenterStart
                ) {
                    AppText(
                        text = "Selasa, 24 Oktober 2023",
                        textStyle = Typography.titleMedium(),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }
            }

            // Gambar & Teks "Pukul"
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            ) {
                Image(
                    imageVector = Icons.Default.AccessTimeFilled,
                    contentDescription = "Calendar icon",
                    colorFilter = ColorFilter.tint(Color.Gray),
                    modifier = Modifier.size(24.dp)
                )

                AppText(
                    text = "Pukul",
                    textStyle = Typography.labelLarge(),
                    color = Color.Gray
                )
            }

            // Field "Pukul"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                        .width(380.dp)
                        .height(56.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(consulationDetail_containerBackground),
                    contentAlignment = Alignment.CenterStart
                ) {
                    AppText(
                        text = "13:00 - 14:00",
                        textStyle = Typography.titleMedium(),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }
            }

            // Gambar & Teks "Hadir secara"
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            ) {
                Image(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person icon",
                    colorFilter = ColorFilter.tint(Color.Gray),
                    modifier = Modifier.size(24.dp)
                )

                AppText(
                    text = "Hadir secara",
                    textStyle = Typography.labelLarge(),
                    color = Color.Gray
                )
            }

            // Field "Hadir secara"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                        .width(380.dp)
                        .height(56.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(consulationDetail_containerBackground),
                    contentAlignment = Alignment.CenterStart
                ) {
                    AppText(
                        text = "Luring",
                        textStyle = Typography.titleMedium(),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewScr() {
    ConsultationDetailScreen(navController = rememberNavController())
}