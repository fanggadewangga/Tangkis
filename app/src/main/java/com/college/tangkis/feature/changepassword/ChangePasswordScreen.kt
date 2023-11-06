package com.college.tangkis.feature.changepassword

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(navController: NavController) {

    val viewModel = hiltViewModel<ChangePasswordViewModel>()
    val changePasswordState = viewModel.changePasswordState.collectAsStateWithLifecycle()
    val logoutState = viewModel.logoutState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(changePasswordState.value) {
        when (changePasswordState.value) {
            is Resource.Error -> Toasty.error(context, changePasswordState.value.message.toString(), Toast.LENGTH_SHORT).show()
            is Resource.Success -> {
                Toasty.success(context, "Berhasil mengubah password!", Toast.LENGTH_SHORT).show()
                viewModel.logout()
            }
            else -> {}
        }
    }

    LaunchedEffect(logoutState.value) {
        when (logoutState.value) {
            is Resource.Error -> Toasty.error(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
            is Resource.Success -> {
                Toasty.success(context, "Berhasil logout!", Toast.LENGTH_SHORT).show()
                coroutineScope.launch {
                    delay(1500L)
                }
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Profile.route) {
                        inclusive = true
                    }
                }
            }
            else -> {}
        }
    }

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

            }

            if (changePasswordState.value is Resource.Loading)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CircularProgressIndicator(color = md_theme_light_primary)
                }
            else
                AppButton(
                    content = {
                        AppText(
                            text = "Ubah Password",
                            color = Color.White,
                            textStyle = Typography.labelLarge()
                        )
                    },
                    onClick = {
                        viewModel.changePassword()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
        }
    }
}