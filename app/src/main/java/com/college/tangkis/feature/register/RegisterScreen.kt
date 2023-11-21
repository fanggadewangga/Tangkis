package com.college.tangkis.feature.register

import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.data.Resource
import com.college.tangkis.feature.main.components.AppButton
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.components.AppTextField
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary
import es.dmoral.toasty.Toasty

@Composable
fun RegisterScreen(navController: NavController) {
    val viewModel = hiltViewModel<RegisterViewModel>()
    val registerState = viewModel.registerState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(registerState.value) {
        when (registerState.value) {
            is Resource.Error -> Toasty.error(
                context,
                registerState.value.message.toString(),
                Toast.LENGTH_SHORT
            ).show()

            is Resource.Success -> {
                Toasty.success(context, "Berhasil daftar!", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Register.route) {
                        inclusive = true
                    }
                }
            }

            else -> {}
        }
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 48.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Box
        AsyncImage(
            model = R.drawable.iv_app_logo,
            contentDescription = "Background",
            modifier = Modifier
                .width(292.dp)
                .height(119.dp)
                .align(Alignment.CenterHorizontally)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = Color.White)
                .padding(horizontal = 16.dp),
        ) {

            Spacer(modifier = Modifier.height(32.dp))
            AppText(
                text = "Daftar Akun",
                textStyle = Typography.titleLarge(),
                modifier = Modifier.align(Alignment.Start)
            )
            AsyncImage(
                model = R.drawable.iv_register_alert,
                contentDescription = "Registration alert",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            AppTextField(
                placeHolder = "Masukkan Nama Lengkap",
                label = "Nama Lengkap",
                value = viewModel.nameState.value,
                onValueChange = {
                    viewModel.apply {
                        isNameFieldClicked.value = true
                        nameState.value = it
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                shape = RoundedCornerShape(4.dp),
                isError = viewModel.isValidName.value,
                showWarningMessage = viewModel.isValidName.value,
                modifier = Modifier.fillMaxWidth()
            )

            AppTextField(
                placeHolder = "Masukkan NIM",
                label = "Nomor Induk Mahasiswa",
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

            AppTextField(
                placeHolder = "Masukkan Nomor Whatsapp",
                label = "Nomor Whatsapp",
                value = viewModel.phoneNumber.value,
                onValueChange = {
                    viewModel.apply {
                        isPhoneNumberFieldClicked.value = true
                        phoneNumber.value = it
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                leadingIcon = {
                    AppText(
                        text = "+62",
                        textStyle = Typography.bodyLarge().copy(lineHeight = 0.sp),
                        modifier = Modifier.padding(bottom = 2.dp,start = 4.dp, end = 0.dp)
                    )
                },
                shape = RoundedCornerShape(4.dp),
                isError = viewModel.isValidPhoneNumber.value,
                showWarningMessage = viewModel.isValidPhoneNumber.value,
                modifier = Modifier.fillMaxWidth()
            )

            AppTextField(
                isPassword = true,
                placeHolder = "Masukkan Password",
                label = "Password",
                value = viewModel.passwordState.value,
                onValueChange = {
                    viewModel.passwordState.value = it
                },
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                ),
                shape = RoundedCornerShape(4.dp),
                isError = viewModel.isValidPasswordState.value,
                showWarningMessage = viewModel.isValidPasswordState.value,
                warningMessage = "Minimal 8 karakter!",
                modifier = Modifier.fillMaxWidth()
            )

            AppTextField(
                isPassword = true,
                placeHolder = "Masukkan Konfirmasi Password",
                label = "Konfirmasi Password",
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

            // Checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = viewModel.isChecked.value, onCheckedChange = {
                        viewModel.isChecked.value = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = md_theme_light_primary,
                        checkmarkColor = Color.White
                    )
                )
                AppText(text = "Saya setuju dengan ", textStyle = Typography.bodyMedium())
                AppText(
                    text = "ketentuan ",
                    textStyle = Typography.titleSmall(),
                    color = md_theme_light_primary
                )
                AppText(text = "dan ", textStyle = Typography.bodyMedium())
                AppText(
                    text = "kebijakan privasi",
                    textStyle = Typography.titleSmall(),
                    maxLine = 1,
                    color = md_theme_light_primary
                )
            }

            // Button
            Spacer(modifier = Modifier.height(48.dp))
            if (registerState.value is Resource.Loading)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CircularProgressIndicator(color = md_theme_light_primary)
                }
            else
                AppButton(
                    content = { AppText(text = "Daftar", color = Color.White) },
                    onClick = {
                       viewModel.register()
                    },
                    modifier = Modifier.fillMaxWidth()
                )


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 16.dp),
            ) {
                AppText(text = "Sudah punya akun?", textStyle = Typography.bodyMedium())
                AppText(
                    text = " Masuk",
                    textStyle = Typography.labelLargeProminent(),
                    color = md_theme_light_primary,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Login.route)
                    }
                )
            }

        }
    }
}