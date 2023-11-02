package com.college.tangkis.feature.report

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.consulationDetail_containerBackground
import com.college.tangkis.theme.md_theme_light_primary
import com.college.tangkis.theme.status_diproses_background
import com.college.tangkis.theme.status_diproses_text
import com.college.tangkis.theme.status_selesai_background
import com.college.tangkis.theme.status_selesai_text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportDetailScreen(navController: NavController) {
    val viewModel = hiltViewModel<ReportDetailViewModel>()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AppText(
                        text = "Detail Laporan",
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
                    text = "Laporan",
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
                        .background(status_diproses_background),
                    contentAlignment = Alignment.Center
                ) {
                    AppText(
                        text = "Diproses",
                        textStyle = Typography.labelStatus(),
                        color = status_diproses_text,
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

            // Teks "Nama Pelapor"
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            ) {
                AppText(
                    text = "Nama Pelapor",
                    textStyle = Typography.labelLarge(),
                    color = Color.Gray
                )
            }

            // Field "Nama Pelapor"
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
                        text = "Abimanyu Danu Nurdewanto",
                        textStyle = Typography.titleMedium(),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }
            }

            // Teks "NIM Pelapor"
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            ) {
                AppText(
                    text = "NIM Pelapor",
                    textStyle = Typography.labelLarge(),
                    color = Color.Gray
                )
            }

            // Field "NIM Pelapor"
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
                        text = "215150201111034",
                        textStyle = Typography.titleMedium(),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }
            }

            // Teks "Nomor WhatsApp Pelapor"
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            ) {
                AppText(
                    text = "Nomor WhatsApp Pelapor",
                    textStyle = Typography.labelLarge(),
                    color = Color.Gray
                )
            }

            // Field "Nomor WhatsApp Pelapor"
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
                        text = "081249967295",
                        textStyle = Typography.titleMedium(),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }
            }

            // Teks "Cerita Kejadian"
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            ) {
                AppText(
                    text = "Cerita Kejadian",
                    textStyle = Typography.labelLarge(),
                    color = Color.Gray
                )
            }

            // Field "Cerita Kejadian"
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
                        .sizeIn(minHeight = 56.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(consulationDetail_containerBackground),
                    contentAlignment = Alignment.CenterStart
                ) {
                    AppText(
                        text = " nec ac quam. Fusce",
                        textStyle = Typography.titleMedium(),
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewScr() {
    ReportDetailScreen(navController = rememberNavController())
}