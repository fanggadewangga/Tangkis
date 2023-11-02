package com.college.tangkis.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.data.Resource
import com.college.tangkis.feature.main.components.AccountConfigurationItem
import com.college.tangkis.feature.main.components.AppDialog
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.components.ErrorLayout
import com.college.tangkis.feature.main.navigation.BottomNavigationBar
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_secondary

@Composable
fun ProfileScreen(navController: NavController) {

    val viewModel = hiltViewModel<ProfileViewModel>()
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val profileState = viewModel.profileState.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.SOS.route) },
                shape = CircleShape,
                containerColor = md_theme_light_secondary,
                modifier = Modifier.size(80.dp)
            ) {
                AsyncImage(
                    model = R.drawable.ic_sos,
                    contentDescription = "SOS",
                    modifier = Modifier.size(42.dp)
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                tonalElevation = 8.dp,
                containerColor = Color.White,
            ) {
                BottomNavigationBar(navController = navController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true
    ) {

        val bottomPadding = it.calculateBottomPadding()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF1F0F0))
        ) {

            // Top Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp)
                    .height((screenHeight * 0.67).dp)
            ) {

                when (profileState.value) {
                    is Resource.Loading -> {}
                    is Resource.Error -> {
                        Spacer(modifier = Modifier.height(48.dp))
                        ErrorLayout(
                            image = R.drawable.iv_error,
                            message = "Profil Gagal Ditampilkan",
                            modifier = Modifier.align(CenterHorizontally)
                        )
                    }

                    is Resource.Success -> {
                        // Image
                        AsyncImage(
                            model = R.drawable.iv_profile,
                            contentDescription = "Profile Illustration",
                            modifier = Modifier.align(CenterHorizontally)
                        )

                        // User Info
                        Spacer(modifier = Modifier.height(16.dp))
                        AppText(
                            text = "Nama Lengkap",
                            textStyle = Typography.titleSmall(),
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            AppText(
                                text = profileState.value.data!!.name,
                                textStyle = Typography.titleMedium(),
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        AppText(
                            text = "Nomor Induk Mahasiswa",
                            textStyle = Typography.titleSmall(),
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            AppText(
                                text =  profileState.value.data!!.nim,
                                textStyle = Typography.titleMedium(),
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        AppText(
                            text = "Nomor Whatsapp",
                            textStyle = Typography.titleSmall(),
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                        ) {
                            AppText(
                                text =  profileState.value.data!!.whatsapp,
                                textStyle = Typography.titleMedium(),
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                            )
                        }
                    }

                    else -> {}
                }
            }

            // Bottom Section
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp)
                    .height((screenHeight * 0.5).dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                AppText(text = "Akun", textStyle = Typography.titleLarge())

                Spacer(modifier = Modifier.height(8.dp))
                AccountConfigurationItem(
                    icon = R.drawable.ic_phone,
                    title = "Ganti Nomor Whatsapp",
                    onClick = {

                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                AccountConfigurationItem(
                    icon = R.drawable.ic_password,
                    title = "Ganti Password",
                    onClick = {

                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                AccountConfigurationItem(
                    icon = R.drawable.ic_logout,
                    title = "Keluar",
                    titleColor = md_theme_light_secondary,
                    onClick = {
                        viewModel.showLogoutDialog.value = true
                    }
                )
            }
        }

        // Dialog
        if (viewModel.showLogoutDialog.value)
            AppDialog(
                dialogContent = {
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Row {
                            AppText(text = "Yakin ", textStyle = Typography.titleSmall())
                            AppText(
                                text = "log out ",
                                textStyle = Typography.titleSmall(),
                                color = Color.Red
                            )
                            AppText(text = "dari", textStyle = Typography.titleSmall())
                        }
                        AppText(text = "akun kamu?", textStyle = Typography.titleSmall())
                    }
                },
                setShowDialog = { isShow ->
                    viewModel.showLogoutDialog.value = isShow
                },
                onCancelClicked = { viewModel.showLogoutDialog.value = false },
                onConfirmClicked = {
                    viewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Profile.route) {
                            inclusive = true
                        }
                    }
                }
            )
    }
}