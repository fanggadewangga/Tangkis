package com.college.tangkis.feature.sos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.feature.main.components.AppButton
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.components.ContactReceiverItem
import com.college.tangkis.feature.main.components.HomeArticleItem
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary
import com.college.tangkis.theme.md_theme_light_secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SosSentScreen(navController: NavController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
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
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Location
                AppText(
                    text = "Hubungi ULTKSP",
                    textStyle = Typography.titleLarge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                AppText(
                    text = "ULTKSP adalah layanan bagi Mahasiswa FILKOM untuk memberikan layanan informasi terkait dugaan Kekerasan Seksual dan Perundungan.",
                    textStyle = Typography.bodyMedium(),
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 16.dp)
                )


                // Button
                Spacer(modifier = Modifier.height(16.dp))
                AppButton(
                    onClick = { /*TODO*/ },
                    backgroundColor = md_theme_light_secondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Phone",
                            colorFilter = ColorFilter.tint(color = Color.White)
                        )
                        AppText(
                            text = "Hubungi ULTKSP",
                            color = Color.White,
                            textStyle = Typography.labelLarge()
                        )
                    }
                }
            }
        }
    ) {
        val topPadding = it.calculateTopPadding()
        val bottomPadding = it.calculateBottomPadding()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight * 0.23).dp)
                    .background(
                        color = md_theme_light_primary,
                        shape = RoundedCornerShape(bottomStart = 48.dp, bottomEnd = 48.dp)
                    )
                    .padding(
                        top = topPadding,
                        start = 24.dp,
                        end = 24.dp
                    )
            ) {
                AppText(
                    text = "Pesan Darurat Terkirim",
                    textStyle = Typography.headlineSmall(),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                AppText(
                    text = "Pesan darurat telah terkirim ke kontak darurat kamu. Tetap tenang dan waspada! ",
                    textStyle = Typography.bodyMedium(),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

            }

            // Location
            Spacer(modifier = Modifier.height(24.dp))
            AppText(
                text = "Lokasimu saat ini",
                textStyle = Typography.titleLarge(),
                modifier = Modifier.padding(start = 16.dp)
            )
            AppText(
                text = "Kamu berada di Gedung F, Fakultas Ilmu Komputer UB ",
                textStyle = Typography.bodyMedium(),
                color = Color.Gray,
                modifier = Modifier.padding(start = 16.dp)
            )

            // Emergency contact
            Spacer(modifier = Modifier.height(24.dp))
            AppText(
                text = "Kontak Darurat",
                textStyle = Typography.titleLarge(),
                modifier = Modifier.padding(start = 16.dp)
            )
            AppText(
                text = "Kamu telah mengirim pesan darurat ke kontak berikut",
                textStyle = Typography.bodyMedium(),
                color = Color.Gray,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(count = 3) {
                    ContactReceiverItem(
                        contactName = "Abim TIF UB-21",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
        }
    }
}