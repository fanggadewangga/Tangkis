package com.college.tangkis.feature.report

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
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.data.Resource
import com.college.tangkis.feature.main.components.AppButton
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary
import com.college.tangkis.theme.md_theme_light_primaryContainer
import com.college.tangkis.theme.md_theme_light_secondary
import com.college.tangkis.theme.status_diproses_background
import com.college.tangkis.theme.status_diproses_text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportDetailScreen(navController: NavController, reportId: String) {

    val viewModel = hiltViewModel<ReportDetailViewModel>()
    val reportDetailState = viewModel.reportDetailState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.getReportDetail(reportId)
    }

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
        },
        bottomBar = {
            if (reportDetailState.value is Resource.Success) {
                if (reportDetailState.value.data!!.isNeedConsultation)
                    AppButton(
                        onClick = {
                            navController.navigate(
                                Screen.ConsultationDetail.route.replace(
                                    oldValue = "{consultationId}",
                                    newValue = reportDetailState.value.data!!.consultationId!!
                                )
                            )
                        },
                        backgroundColor = md_theme_light_secondary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 24.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                            ) {
                                Image(
                                    imageVector = Icons.Default.Today,
                                    contentDescription = "Calendar icon",
                                    colorFilter = ColorFilter.tint(color = Color.White)
                                )
                                AppText(text = "Lihat Jadwal Pendapingan", color = Color.White)
                            }
                        }
                    }
            }
        }
    ) {
        val topPadding = it.calculateTopPadding()

        when (reportDetailState.value) {
            is Resource.Loading -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(color = md_theme_light_primary)
                }
            }

            is Resource.Error -> {
            }
            is Resource.Success -> {
                val reportDetail = reportDetailState.value.data!!
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = topPadding)
                        .verticalScroll(rememberScrollState())
                        .background(color = Color.White)
                ) {

                    // Date & Time
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AppText(
                            text = "Laporan",
                            textStyle = Typography.labelLarge(),
                            color = Color.Gray,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        AppText(
                            text = reportDetail.date,
                            textStyle = Typography.bodyMedium(),
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
                                text = reportDetail.progress,
                                textStyle = Typography.labelStatus(),
                                color = status_diproses_text,
                                modifier = Modifier
                                    .padding(horizontal = 12.dp, vertical = 3.dp)
                            )
                        }

                    }

                    // Divider (Garis)
                    Divider(
                        thickness = 1.dp,
                        color = Color.LightGray,
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
                            .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                    ) {
                        AppText(
                            text = "Nama Pelapor",
                            textStyle = Typography.labelLargeProminent(),
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
                                .background(md_theme_light_primaryContainer),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            AppText(
                                text = reportDetail.name,
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
                            .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                    ) {
                        AppText(
                            text = "NIM Pelapor",
                            textStyle = Typography.labelLargeProminent(),
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
                                .background(md_theme_light_primaryContainer),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            AppText(
                                text = reportDetail.nim,
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
                            .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                    ) {
                        AppText(
                            text = "Nomor WhatsApp Pelapor",
                            textStyle = Typography.labelLargeProminent(),
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
                                .background(md_theme_light_primaryContainer),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            AppText(
                                text = reportDetail.whatsapp,
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
                            .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                    ) {
                        AppText(
                            text = "Cerita Kejadian",
                            textStyle = Typography.labelLargeProminent(),
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
                                .background(md_theme_light_primaryContainer),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            AppText(
                                text = reportDetail.story,
                                textStyle = Typography.titleMedium(),
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 16.dp)
                            )
                        }
                    }
                }
            }

            else -> {}
        }
    }
}