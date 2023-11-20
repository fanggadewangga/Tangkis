package com.college.tangkis.feature.sos

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.feature.main.components.AppButton
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.constant.Constants.FILKOM_LOCATION
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.feature.main.utils.getCurrentLocation
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary
import com.college.tangkis.theme.md_theme_light_secondary
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SosScreen(navController: NavController) {

    val viewModel = hiltViewModel<SosViewModel>()
    val interactionSource = remember { MutableInteractionSource() }
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val context = LocalContext.current
    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            viewModel.isLocationPermissionGranted.value = isGranted
            viewModel.isSMSPermissionGranted.value = isGranted
            if (isGranted) {
                getCurrentLocation(context) {
                    viewModel.apply {
                        userLatState.doubleValue = it.latitude
                        userLonState.doubleValue = it.longitude
                        getAddressFromCoordinate(context)
                    }
                }
            }
        }
    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION,
        ),
        -> {
            viewModel.isLocationPermissionGranted.value = true
            getCurrentLocation(context) {
                viewModel.apply {
                    userLatState.doubleValue = it.latitude
                    userLonState.doubleValue = it.longitude
                    getAddressFromCoordinate(context)
                }
            }
        }

        else -> {
            SideEffect {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }
    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ),
        -> {
            viewModel.isLocationPermissionGranted.value = true
            getCurrentLocation(context) {
                viewModel.apply {
                    userLatState.doubleValue = it.latitude
                    userLonState.doubleValue = it.longitude
                    getAddressFromCoordinate(context)
                }
            }
        }

        else -> {
            SideEffect {
                permissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            }
        }
    }
    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.SEND_SMS,
        ),
        -> {
            viewModel.isSMSPermissionGranted.value = true
        }

        else -> {
            SideEffect {
                permissionLauncher.launch(Manifest.permission.SEND_SMS)
            }
        }
    }

    LaunchedEffect(viewModel.isLocationPermissionGranted.value) {
        val userLocation = Location("User")
        val filkomLocation = Location("Filkom")
        userLocation.apply {
            latitude = viewModel.userLatState.doubleValue
            longitude = viewModel.userLonState.doubleValue
        }
        filkomLocation.apply {
            latitude = FILKOM_LOCATION.latitude
            longitude = FILKOM_LOCATION.longitude
        }
        val distance = filkomLocation.distanceTo(userLocation) / 1000
        viewModel.isInRange.value = distance <= 3.0
    }

    LaunchedEffect(!viewModel.isSMSPermissionGranted.value) {
        permissionLauncher.launch(Manifest.permission.SEND_SMS)
    }

    LaunchedEffect(!viewModel.isLocationPermissionGranted.value) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        permissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    LaunchedEffect(key1 = viewModel.timeLeft.intValue, key2 = viewModel.isSending.value) {
        while (viewModel.timeLeft.intValue > 0 && viewModel.isSending.value) {
            delay(1000L)
            viewModel.timeLeft.intValue--
            if (viewModel.timeLeft.intValue == 0) {
                viewModel.contactState.value.data!!.forEach {
                    viewModel.sentEmergencyMessage(destinationNumber = it.number, message = "Tes SOS")
                }
                navController.navigate(Screen.SosSent.route)
            }
        }
    }

    DisposableEffect(true) {
        onDispose {
            viewModel.resetState()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AppText(
                        text = "Pesan Darurat",
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
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
            ) {
                AsyncImage(model = R.drawable.iv_sos_alert, contentDescription = "SOS Alert")

                if (viewModel.isSending.value)
                    AppButton(
                        onClick = {
                            viewModel.apply {
                                isSending.value = false
                                resetState()
                            }
                        },
                        backgroundColor = Color.White,
                        borderColor = md_theme_light_secondary,
                        borderWidth = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        AppText(
                            text = "Batalkan",
                            color = md_theme_light_secondary,
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
                .fillMaxSize()
                .background(Color.White)
        ) {

            if (viewModel.isSending.value) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((screenHeight * 0.7).dp)
                        .background(
                            color = md_theme_light_primary,
                            shape = RoundedCornerShape(bottomStart = 48.dp, bottomEnd = 48.dp)
                        )
                        .padding(
                            top = topPadding + 48.dp,
                            bottom = bottomPadding,
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    AppText(
                        text = "Pesan Darurat Segera Terkirim",
                        textStyle = Typography.headlineSmall(),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AppText(
                        text = "Pesan daruratmu akan segera terkirim melalui SMS ke kontak daruratmu",
                        textStyle = Typography.bodyMedium(),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    AppText(
                        text = "0${viewModel.timeLeft.intValue}",
                        textStyle = TextStyle(
                            fontFamily = FontFamily(
                                Font(R.font.roboto_black)
                            ),
                            fontSize = 200.sp
                        ),
                        color = Color.White,
                        modifier = Modifier.padding(top = 48.dp)
                    )
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(
                            top = topPadding + 48.dp,
                            bottom = bottomPadding,
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    AppText(
                        text = if (viewModel.isInRange.value) "Kamu dalam bahaya?" else "SOS tidak aktif",
                        textStyle = Typography.headlineSmall(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AppText(
                        text = if (viewModel.isInRange.value) "Tekan tombol SOS maka, pesan darurat akan dikirimkan ke kontak darurat kamu melalui SMS" else "Tombol tidak aktif karena kamu berada di luar area Fakultas Ilmu Komputer Universitas Brawijaya.",
                        textStyle = Typography.bodyMedium(),
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }

                // Button SOS
                Card(
                    colors = CardDefaults.cardColors(if (viewModel.isInRange.value) md_theme_light_secondary else Color.Gray),
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 16.dp
                    ),
                    modifier = Modifier
                        .clickable(
                            enabled = viewModel.isInRange.value,
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            viewModel.isSending.value = true
                        }
                        .shadow(
                            elevation = 44.dp,
                            spotColor = Color(0x40F02727),
                            ambientColor = Color(0x40F02727),
                            shape = CircleShape
                        )
                        .align(Alignment.Center)
                ) {
                    AsyncImage(
                        model = R.drawable.ic_sos,
                        contentDescription = "SOS",
                        modifier = Modifier
                            .size(240.dp)
                            .padding(56.dp)
                    )
                }
            }
        }
    }
}