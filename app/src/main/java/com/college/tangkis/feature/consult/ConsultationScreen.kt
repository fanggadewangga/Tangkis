package com.college.tangkis.feature.consult

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.feature.main.components.AppButton
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.components.AppTextField
import com.college.tangkis.feature.main.constant.Constants
import com.college.tangkis.feature.report.AccompanimentLayout
import com.college.tangkis.feature.report.ReportForm
import com.college.tangkis.feature.report.ReportViewModel
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultationScreen(navController: NavController) {

    val viewModel = hiltViewModel<ConsultationViewModel>()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AppText(
                        text = if (viewModel.screenIndex.intValue == 1) "Konsultasi" else "Jadwal Konsultasi",
                        color = Color.White,
                        textStyle = Typography.titleLarge()
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = md_theme_light_primary,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        if (viewModel.screenIndex.intValue == 1)
                            navController.popBackStack()
                        else
                            viewModel.screenIndex.intValue -= 1
                    }) {
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
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                AsyncImage(
                    model = if (viewModel.screenIndex.intValue == 1) R.drawable.iv_user_data_is_save else R.drawable.iv_correct_schedule,
                    contentDescription = "Your Data is Save",
                    modifier = Modifier.fillMaxWidth()
                )
                AppButton(
                    onClick = {
                        if (viewModel.screenIndex.intValue == 1)
                            viewModel.screenIndex.intValue = 2
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                ) {
                    AppText(
                        text = if (viewModel.screenIndex.intValue == 1) "Lanjutkan" else "Konfirmasi",
                        color = Color.White,
                        textStyle = Typography.labelLarge()
                    )
                }
            }
        }
    ) {
        val topPadding = it.calculateTopPadding()
        val bottomPadding = it.calculateBottomPadding()

        Box(
            modifier = Modifier
                .padding(
                    bottom = bottomPadding + 16.dp,
                    top = topPadding + 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
                .background(color = Color.White)
        ) {
            if (viewModel.screenIndex.intValue == 1)
                ConsultationForm(viewModel = viewModel)
            else
                ConsultationLayout(viewModel = viewModel)
        }
    }
}

@Composable
fun ConsultationForm(viewModel: ConsultationViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {

        AppText(text = "Ayo isi formulir pelaporan", textStyle = Typography.titleLarge())
        AppText(
            text = "Konseling meliputi masalah pribadi, sosial, bimbingan belajar, dan bimbingan karir.",
            textStyle = Typography.bodyMedium(),
            color = Color.Gray
        )
        AppTextField(
            placeHolder = "Nomor Whatsapp *",
            value = "+6281330723755",
            onValueChange = {},
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )
        AppTextField(
            placeHolder = "Tolong ceritakan apa yang terjadi padamu! *",
            value = viewModel.story.value,
            onValueChange = {
                viewModel.story.value = it
            },
            showWarningMessage = true,
            warningMessage = "Tenang, privasimu pasti kami jaga",
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(top = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            AppText(
                text = "Silahkan pilih mau konseling bersama siapa?",
                textStyle = Typography.labelLargeProminent()
            )
            AppText(
                text = "*",
                textStyle = Typography.labelLargeProminent(),
                color = Color.Red
            )
        }
        AppText(
            text = "Konseling ULTKSP adalah konseling bersama tenaga ahli di bidangnya. Sementara konseling sebaya merupakan konseling dengan teman sebaya yang telah mendapatkan pelatihan khusus.",
            textStyle = Typography.bodySmall(),
            color = Color.Gray
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(64.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // 1st Choice
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = viewModel.isNeedAccompaniment.value,
                    onClick = { viewModel.isNeedAccompaniment.value = true },
                    colors = RadioButtonDefaults.colors(selectedColor = md_theme_light_primary)
                )
                AppText(
                    text = "ULTKSP",
                    textStyle = Typography.labelLargeProminent(),
                    color = Color.Gray
                )
            }
            // 2nd Choice
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = !viewModel.isNeedAccompaniment.value,
                    onClick = { viewModel.isNeedAccompaniment.value = false },
                    colors = RadioButtonDefaults.colors(selectedColor = md_theme_light_primary)
                )
                AppText(
                    text = "Sebaya",
                    textStyle = Typography.labelLargeProminent(),
                    color = Color.Gray
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultationLayout(viewModel: ConsultationViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Row {
            AppText(
                text = "Silahkan pilih jadwal pendampingan ",
                textStyle = Typography.titleLarge()
            )
            AppText(text = "*", textStyle = Typography.titleLarge(), color = Color.Red)
        }
        AppText(
            text = "Pastikan jadwal yang dipilih tersedia dan sesuai dengan jadwal kamu.",
            textStyle = Typography.bodyMedium(),
            color = Color.Gray
        )

        // Date

        // Accompaniment Type
        Spacer(modifier = Modifier.height(80.dp))
        Row {
            AppText(
                text = "Ketersediaan mahasiswa untuk hadir konseling ",
                textStyle = Typography.labelLargeProminent()
            )
            AppText(text = "*", textStyle = Typography.labelLargeProminent(), color = Color.Red)
        }
        AppText(
            text = "Pendampingan yang dimaksud berupa pendampingan psikologis dan/atau pelaporan hukum.",
            color = Color.Gray,
            textStyle = Typography.bodySmall()
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(64.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // 1st Choice
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = viewModel.accompanimentTypeIndex.intValue == 0,
                    onClick = { viewModel.accompanimentTypeIndex.intValue = 0 },
                    colors = RadioButtonDefaults.colors(selectedColor = md_theme_light_primary)
                )
                AppText(
                    text = "Daring",
                    textStyle = Typography.labelLargeProminent(),
                    color = Color.Gray
                )
            }
            // 2nd Choice
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = viewModel.accompanimentTypeIndex.intValue == 1,
                    onClick = { viewModel.accompanimentTypeIndex.intValue = 1 },
                    colors = RadioButtonDefaults.colors(selectedColor = md_theme_light_primary)
                )
                AppText(
                    text = "Luring",
                    textStyle = Typography.labelLargeProminent(),
                    color = Color.Gray
                )
            }
        }

        // Hour
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            ExposedDropdownMenuBox(
                expanded = viewModel.isDropdownExpanded.value,
                onExpandedChange = {
                    viewModel.isDropdownExpanded.value = !viewModel.isDropdownExpanded.value
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                TextField(
                    value = if (viewModel.selectedTimeIndex.intValue == 100) "Tentukan Jam" else "${Constants.CONSULTATION_TIME[viewModel.selectedTimeIndex.intValue].startTime} - ${Constants.CONSULTATION_TIME[viewModel.selectedTimeIndex.intValue].endTime}",
                    onValueChange = {},
                    readOnly = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        focusedIndicatorColor = md_theme_light_primary,
                        textColor = if (viewModel.selectedTimeIndex.intValue == 100) md_theme_light_primary else Color.Black
                    ),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = viewModel.isDropdownExpanded.value) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .background(Color.White)
                        .border(width = 1.dp, color = md_theme_light_primary)
                )

                ExposedDropdownMenu(
                    expanded = viewModel.isDropdownExpanded.value,
                    onDismissRequest = { viewModel.isDropdownExpanded.value = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Constants.CONSULTATION_TIME.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            text = { Text(text = "${item.startTime} - ${item.endTime}") },
                            onClick = {
                                viewModel.apply {
                                    viewModel.selectedTimeIndex.intValue = index
                                    hideDropdown()
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
