package com.college.tangkis.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel = hiltViewModel<LoginViewModel>()
    LocalConfiguration.current.screenWidthDp

    Column(
        horizontalAlignment = Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 48.dp)
    ) {
        // Box
        AsyncImage(
            model = R.drawable.iv_app_logo,
            contentDescription = "Background",
            modifier = Modifier
                .width(292.dp)
                .height(119.dp)
                .align(CenterHorizontally)
        )

        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .background(color = Color.White)
                .padding(horizontal = 16.dp),
        ) {

            Spacer(modifier = Modifier.height(32.dp))
            AppText(
                text = "Selamat Datang",
                textStyle = Typography.titleLarge(),
                modifier = Modifier.align(Start)
            )
            AppText(
                text = "Silahkan masukkan NIM dan password SIAM kamu!",
                textStyle = Typography.bodyMedium(),
                color = Color.Gray,
                modifier = Modifier
                    .align(Start)
                    .padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            AppTextField(
                placeHolder = "Nomor Induk Mahasiswa",
                value = viewModel.studentNumberState.value,
                onValueChange = {
                    viewModel.apply {
                        isStudentNumberFieldClicked.value = true
                        studentNumberState.value = it
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                shape = RoundedCornerShape(4.dp),
                isError = viewModel.isValidStudentNumber.value,
                showWarningMessage = viewModel.isValidStudentNumber.value,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            AppTextField(
                isPassword = true,
                placeHolder = "Password",
                value = viewModel.passwordState.value,
                onValueChange = {
                    viewModel.passwordState.value = it
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                shape = RoundedCornerShape(4.dp),
                isError = viewModel.isValidPasswordState.value,
                showWarningMessage = viewModel.isValidPasswordState.value,
                warningMessage = "Password harus lebih dari 8 karakter!",
                modifier = Modifier.fillMaxWidth()
            )

            // Button
            Spacer(modifier = Modifier.height(48.dp))
            AppButton(
                content = { AppText(text = "Masuk", color = Color.White) },
                onClick = {navController.navigate(Screen.Home.route)},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}