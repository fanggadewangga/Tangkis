package com.college.tangkis.feature.faq

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.feature.main.components.AppButton
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.components.ContactDeletionDialog
import com.college.tangkis.feature.main.components.EmergencyContactItem
import com.college.tangkis.feature.main.components.ErrorLayout
import com.college.tangkis.feature.main.components.FaqItem
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaqScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AppText(
                        text = "Frequently Asked Questions",
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
    ) {
        val topPadding = it.calculateTopPadding()

        Column (
            modifier = Modifier
                .padding(top = topPadding + 16.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Tulis pertanyaan dan jawaban FAQ
            FaqItem(
                question = "Bagaimana cara menggunakan SOS?",
                answer = "Kamu dapat menggunakan tombol SOS ketika Anda menghadapi potensi bahaya pelecehan seksual di sekitar area Fakultas Ilmu Komputer."
            )
            FaqItem(
                question = "Siapa yang harus ditambahkan dalam kontak darurat?",
                answer = "Sebaiknya tambahkan nomor kontak teman atau orang yang kamu kenal di Fakultas Ilmu Komputer UB agar pertolongan lebih optimal dan memastikan efisiensi dan efektivitas dalam situasi darurat."
            )
            FaqItem(
                question = "Apakah kerahasiaan data saya terjamin?",
                answer = "Tidak perlu khawatir, privasi dan kerahasiaan Kamu adalah prioritas kami. Segala data dan identitas hanya digunakan untuk kepentingan konseling."
            )
            FaqItem(
                question = "Siapa yang akan melayani saat konseling?",
                answer = "Saat konseling kamu akan dilayani oleh psikolog dari fakultas atau teman sebaya yang sudah terverifikasi keahlianya."
            )
        }
    }
}

@Composable
@Preview
fun FaqScreenPreview(){
    FaqScreen(navController = rememberNavController())
}
