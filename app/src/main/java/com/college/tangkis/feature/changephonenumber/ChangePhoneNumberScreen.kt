package com.college.tangkis.feature.changephonenumber

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.feature.main.components.AppButton
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.components.AppTextField
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeNumberScreen(navController: NavController) {
    //?
    val viewModel = hiltViewModel<ChangePhoneNumberViewModel>()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AppText(
                        text = "Ganti Nomor Whatsapp",
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
    )
    {
        val topPadding = it.calculateTopPadding()
        val bottomPadding = it.calculateBottomPadding()

        Column(
            modifier = Modifier
                .padding(
                    top = topPadding + 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = bottomPadding + 8.dp
                )
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(0.93f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AppText(
                    text = "Ubah ke nomor Whatsapp kamu, sehingga saat melakukan pelaporan dan/atau konsultasi kami dapat menghubungimu",
                    textStyle = Typography.bodyMedium(),
                    color = Color.Gray
                )
                AppTextField(
                    placeHolder = "Nomor Whatsapp Lama",
                    value = viewModel.oldNumberState.value,
                    onValueChange = {
                        viewModel.apply {
                            isOldNumberFieldClicked.value = true
                            oldNumberState.value = it
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    shape = RoundedCornerShape(4.dp),
                    isError = viewModel.isValidOldNumber.value,
                    showWarningMessage = viewModel.isValidOldNumber.value,
                    modifier = Modifier.fillMaxWidth()
                )

                AppTextField(
                    placeHolder = "Nomor Whatsapp Baru",
                    value = viewModel.newNumberState.value,
                    onValueChange = {
                        viewModel.apply {
                            isNewNumberFieldClicked.value = true
                            newNumberState.value = it
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    shape = RoundedCornerShape(4.dp),
                    isError = viewModel.isValidOldNumber.value,
                    showWarningMessage = viewModel.isValidNewNumber.value,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            AppButton(
                content = { AppText(text = "Ubah Nomor Whatsapp", color = Color.White, textStyle = Typography.labelLarge()) },
                onClick = {
                    //TO BE IMPLEMENTED
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}