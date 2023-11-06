package com.college.tangkis.feature.changephonenumber

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeNumberScreen(navController: NavController) {

    val viewModel = hiltViewModel<ChangePhoneNumberViewModel>()
    val userState = viewModel.userState.collectAsStateWithLifecycle()
    val changeWhatsappState = viewModel.changeWhatsappState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(changeWhatsappState.value) {
        when (changeWhatsappState.value) {
            is Resource.Loading -> {}
            is Resource.Error -> Toasty.error(context, changeWhatsappState.value.message.toString(), Toast.LENGTH_SHORT).show()
            is Resource.Empty -> {}
            is Resource.Success -> {
                Toasty.success(context, "Berhasil mengubah nomor Whatsapp!", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Profile.route) {
                    popUpTo(Screen.ChangePhoneNumber.route) {
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
        bottomBar = {
            if (changeWhatsappState.value is Resource.Loading)
                Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(color = md_theme_light_primary)
                }
            else
                AppButton(
                    content = {
                        AppText(
                            text = "Ubah Nomor Whatsapp",
                            color = Color.White,
                            textStyle = Typography.labelLarge()
                        )
                    },
                    onClick = {
                        viewModel.changeWhatsapp()
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 24.dp)
                )
        }
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
                    value = if (userState.value is Resource.Success) userState.value.data!!.whatsapp else "",
                    onValueChange = {},
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    enabled = false,
                    shape = RoundedCornerShape(4.dp),
                    isError = false,
                    showWarningMessage = false,
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
                    isError = viewModel.isValidNewNumber.value,
                    showWarningMessage = viewModel.isValidNewNumber.value,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}