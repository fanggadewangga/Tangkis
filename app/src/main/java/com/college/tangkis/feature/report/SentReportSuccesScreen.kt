package com.college.tangkis.feature.report

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.feature.main.components.AppButton
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary

@Composable
fun SentReportSuccessScreen(navController: NavController, reportId: String = "") {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 16.dp, top = 32.dp, end = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.TopCenter)
        ) {
            AppText(
                text = "Laporan Terkirim",
                textAlign = TextAlign.Center,
                textStyle = Typography.headlineSmall()
            )
            Spacer(modifier = Modifier.height(16.dp))
            AppText(
                text = "Laporanmu telah diterima. Silahkan menunggu update selanjutnya pada halaman aktivitas.",
                textAlign = TextAlign.Center,
                textStyle = Typography.bodyMedium()
            )
            Spacer(modifier = Modifier.height(24.dp))
            AsyncImage(
                model = R.drawable.iv_success,
                contentDescription = "Request success",
                modifier = Modifier.height(360.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(bottom = 48.dp)
        ) {
            AppButton(
                onClick = {
                    navController.navigate(
                        Screen.ReportDetail.route.replace(
                            oldValue = "{reportId}",
                            newValue = reportId
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                AppText(
                    text = "Lihat Laporan",
                    color = Color.White,
                    textStyle = Typography.labelLarge()
                )
            }

            AppText(
                text = "Kembali",
                color = md_theme_light_primary,
                textStyle = Typography.labelLargeProminent(),
                modifier = Modifier.clickable {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.SentReportSuccess.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

    }
}