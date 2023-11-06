package com.college.tangkis.feature.report

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.data.Resource
import com.college.tangkis.feature.main.components.AppButton
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.components.AppTextField
import com.college.tangkis.feature.main.constant.Constants.CONSULTATION_TIME
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import es.dmoral.toasty.Toasty
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(navController: NavController) {

    val viewModel = hiltViewModel<ReportViewModel>()
    val sentReportState = viewModel.reportState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(sentReportState.value) {
        when (sentReportState.value) {
            is Resource.Loading -> {}
            is Resource.Error -> Toasty.error(
                context,
                sentReportState.value.message.toString(),
                Toast.LENGTH_SHORT
            ).show()

            is Resource.Empty -> {}
            is Resource.Success -> {
                Toasty.success(context, "Berhasil membuat laporan!", Toast.LENGTH_SHORT).show()
                navController.navigate(
                    Screen.SentReportSuccess.route.replace(
                        oldValue = "{reportId}",
                        newValue = sentReportState.value.data!!.reportId
                    )
                ) {
                    popUpTo(Screen.SentReportSuccess.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AppText(
                        text = if (viewModel.screenIndex.intValue == 1) "Pelaporan Online" else "Jadwal Pendampingan",
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
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                AsyncImage(
                    model = if (viewModel.screenIndex.intValue == 1) R.drawable.iv_user_data_is_save else R.drawable.iv_correct_schedule,
                    contentDescription = "Your Data is Save",
                    modifier = Modifier.fillMaxWidth()
                )
                if (sentReportState.value is Resource.Loading)
                    CircularProgressIndicator(color = md_theme_light_primary)
                else
                    AppButton(
                        onClick = {
                            if (viewModel.screenIndex.intValue == 1 && viewModel.isNeedAccompaniment.value)
                                viewModel.screenIndex.intValue = 2
                            else
                                viewModel.sentReport()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                    ) {
                        AppText(
                            text = if (viewModel.screenIndex.intValue == 1 && viewModel.isNeedAccompaniment.value) "Lanjutkan" else "Kirim",
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
                ReportForm(viewModel = viewModel)
            else
                AccompanimentLayout(viewModel = viewModel)
        }
    }
}

@Composable
fun ReportForm(viewModel: ReportViewModel) {

    val userState = viewModel.userState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxWidth()) {

        AppText(text = "Ayo isi formulir pelaporan", textStyle = Typography.titleLarge())
        AppText(
            text = "Layanan untuk pendampingan dan pelaporan bagi korban kekerasan seksual dan perundungan",
            textStyle = Typography.bodyMedium(),
            color = Color.Gray
        )
        AppTextField(
            placeHolder = "Nomor Whatsapp *",
            value = if (userState.value is Resource.Success) userState.value.data!!.whatsapp else "",
            onValueChange = {},
            enabled = false,
            disabledIndicatorColor = md_theme_light_primary,
            focusedIndicatorColor = md_theme_light_primary,
            unfocusedIndicatorColor = md_theme_light_primary,
            placeHolderColor = md_theme_light_primary,
            shape = RoundedCornerShape(8.dp),
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
            shape = RoundedCornerShape(8.dp),
            warningMessage = "Tenang, privasimu pasti kami jaga",
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(top = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            AppText(
                text = "Apakah memerlukan bantuan pendampingan lanjutan?",
                textStyle = Typography.labelLargeProminent()
            )
            AppText(
                text = "*",
                textStyle = Typography.labelLargeProminent(),
                color = Color.Red
            )
        }
        AppText(
            text = "Pendampingan yang dimaksud berupa konsultasi psikologis dan/atau pelaporan hukum.",
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
                    text = "Perlu",
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
                    text = "Tidak Perlu",
                    textStyle = Typography.labelLargeProminent(),
                    color = Color.Gray
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccompanimentLayout(viewModel: ReportViewModel) {

    val calendarState = rememberMaterialDialogState()

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
        Spacer(modifier = Modifier.height(24.dp))
        AppTextField(
            placeHolder = "Tanggal",
            value = if (viewModel.dateState.value.isEmpty()) "Pilih Tanggal" else viewModel.formattedDate.value,
            onValueChange = {},
            enabled = false,
            shape = RoundedCornerShape(8.dp),
            disabledIndicatorColor = md_theme_light_primary,
            focusedIndicatorColor = md_theme_light_primary,
            unfocusedIndicatorColor = md_theme_light_primary,
            placeHolderColor = md_theme_light_primary,
            trailingIcon = {
                AsyncImage(
                    model = R.drawable.ic_date,
                    contentDescription = "Date icon",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            calendarState.show()
                        })
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    calendarState.show()
                }
        )
        MaterialDialog(
            shape = RoundedCornerShape(16.dp),
            dialogState = calendarState,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            buttons = {
                positiveButton(text = "Ok", textStyle = TextStyle(color = md_theme_light_primary)) {
                    viewModel.dateState.value = viewModel.formattedDate.value
                    calendarState.hide()
                }
                negativeButton(
                    text = "Cancel",
                    textStyle = TextStyle(color = md_theme_light_primary)
                ) {
                    calendarState.hide()
                }
            },
            backgroundColor = Color.White
        ) {
            datepicker(
                title = "Select date",
                initialDate = LocalDate.now(),
                waitForPositiveButton = true,
                colors = DatePickerDefaults.colors(
                    headerBackgroundColor = md_theme_light_primary,
                    headerTextColor = Color.White,
                    dateActiveBackgroundColor = md_theme_light_primary,
                    dateActiveTextColor = Color.White
                ),
                allowedDateValidator = { date ->
                    date >= LocalDate.now()
                }
            ) {
                viewModel.pickedDate.value = it
            }
        }


        // Accompaniment Type
        Spacer(modifier = Modifier.height(24.dp))
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
                    selected = viewModel.accompanimentTypeIndex.intValue == 1,
                    onClick = { viewModel.accompanimentTypeIndex.intValue = 1 },
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
                    selected = viewModel.accompanimentTypeIndex.intValue == 2,
                    onClick = { viewModel.accompanimentTypeIndex.intValue = 2 },
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
                    value = if (viewModel.selectedTimeIndex.intValue == 100) "Tentukan Jam" else "${CONSULTATION_TIME[viewModel.selectedTimeIndex.intValue].startTime} - ${CONSULTATION_TIME[viewModel.selectedTimeIndex.intValue].endTime}",
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
                    CONSULTATION_TIME.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            text = { Text(text = "${item.startTime} - ${item.endTime}") },
                            onClick = {
                                viewModel.apply {
                                    selectedTimeIndex.intValue = index
                                    selectedTimeId.value = item.consultationTimeId
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