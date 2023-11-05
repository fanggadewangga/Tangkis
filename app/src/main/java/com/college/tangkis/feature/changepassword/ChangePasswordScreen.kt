package com.college.tangkis.feature.changepassword

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.college.tangkis.theme.md_theme_light_secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(navController: NavController) {

    //?
    val viewModel = hiltViewModel<ChangePasswordViewModel>()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AppText(
                        text = "Ganti Password",
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
                    text = "Kamu akan keluar dari semua sesi kecuali sesi ini untuk melindungi akun kamu jika ada orang yang mencoba mendapatkan akses",
                    textStyle = Typography.bodyMedium(),
                    color = Color.Gray
                )

                AppTextField(
                    isPassword = true,
                    placeHolder = "Password Lama",
                    value = viewModel.currentPasswordState.value,
                    onValueChange = {
                        viewModel.currentPasswordState.value = it
                    },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        imeAction = ImeAction.Next
                    ),
                    shape = RoundedCornerShape(4.dp),

                    //Logic for comparing current/old password to be implemented!

                    isError = viewModel.isValidCurrentPasswordState.value,
                    showWarningMessage = viewModel.isValidCurrentPasswordState.value,
                    warningMessage = "Password Tidak Sesuai!",
                    modifier = Modifier.fillMaxWidth()
                )

                AppTextField(
                    isPassword = true,
                    placeHolder = "Password Baru",
                    value = viewModel.newPasswordState.value,
                    onValueChange = {
                        viewModel.newPasswordState.value = it
                    },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        imeAction = ImeAction.Next
                    ),
                    shape = RoundedCornerShape(4.dp),
                    isError = viewModel.isValidNewPasswordState.value,
                    showWarningMessage = viewModel.isValidNewPasswordState.value,
                    warningMessage = "Minimal 8 karakter!",
                    modifier = Modifier.fillMaxWidth()
                )

                AppTextField(
                    isPassword = true,
                    placeHolder = "Konfirmasi Password",
                    value = viewModel.passwordConfirmState.value,
                    onValueChange = {
                        viewModel.passwordConfirmState.value = it
                    },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        imeAction = ImeAction.Next
                    ),
                    shape = RoundedCornerShape(4.dp),
                    isError = viewModel.isValidConfirmPasswordState.value,
                    showWarningMessage = viewModel.isValidConfirmPasswordState.value,
                    warningMessage = "Password tidak sesuai!",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(30.dp))

                AppText(
                    text = "Lupa Password?",
                    textStyle = Typography.titleMedium(),
                    color = md_theme_light_secondary,
                    modifier = Modifier.clickable { },
                )
                
            }
            AppButton(
                content = { AppText(text = "Ubah Password", color = Color.White, textStyle = Typography.labelLarge()) },
                onClick = {
                    //TO BE IMPLEMENTED
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}