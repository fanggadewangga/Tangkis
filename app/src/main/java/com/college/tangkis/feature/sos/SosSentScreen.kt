package com.college.tangkis.feature.sos

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.data.Resource
import com.college.tangkis.feature.main.components.AppButton
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.components.ContactReceiverItem
import com.college.tangkis.feature.main.utils.getCurrentLocation
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary
import com.college.tangkis.theme.md_theme_light_secondary
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SosSentScreen(navController: NavController) {

    val screenHeight = LocalConfiguration.current.screenHeightDp
    val viewModel = hiltViewModel<SosViewModel>()
    val context = LocalContext.current
    LocalContext.current.resources
    val cameraPositionState = rememberCameraPositionState {
        CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 30f)
    }
    val userMarkerState = MarkerState(position = LatLng(0.0, 0.0))
    MarkerState(position = LatLng(0.0, 0.0))
    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            viewModel.isPermissionGranted.value = isGranted
            if (isGranted) {
                getCurrentLocation(context) {
                    viewModel.apply {
                        userLatState.doubleValue = it.latitude
                        userLonState.doubleValue = it.longitude
                        getAddressFromCoordinate(context)
                    }
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 30f)
                    userMarkerState.position = it
                }
            }
        }
    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ),
        -> {
            viewModel.isPermissionGranted.value = true
            getCurrentLocation(context) {
                viewModel.apply {
                    userLatState.doubleValue = it.latitude
                    userLonState.doubleValue = it.longitude
                    getAddressFromCoordinate(context)
                }
                cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 10f)
                userMarkerState.position = it
            }
        }

        else -> {
            SideEffect {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    DisposableEffect(true) {
        onDispose {
            viewModel.resetState()
        }
    }

    val contactState = viewModel.contactState.collectAsStateWithLifecycle()

    LaunchedEffect(contactState.value is Resource.Success && !contactState.value.data.isNullOrEmpty()) {
        contactState.value.data!!.forEach {
            viewModel.sentEmergencyMessage(destinationNumber = it.number, message = "Tes")
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
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
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .shadow(elevation = 1.dp)
            ) {
                // Location
                Spacer(modifier = Modifier.height(12.dp))
                AppText(
                    text = "Hubungi ULTKSP",
                    textStyle = Typography.titleLarge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                AppText(
                    text = "ULTKSP adalah layanan bagi Mahasiswa FILKOM untuk memberikan layanan informasi terkait dugaan Kekerasan Seksual dan Perundungan.",
                    textStyle = Typography.bodyMedium(),
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 16.dp)
                )


                // Button
                Spacer(modifier = Modifier.height(16.dp))
                AppButton(
                    onClick = {
                        val uri = Uri.parse("tel:+6281330723755")
                        val intent = Intent(Intent.ACTION_DIAL, uri)
                        context.startActivity(intent)
                    },
                    backgroundColor = md_theme_light_secondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Phone",
                            colorFilter = ColorFilter.tint(color = Color.White)
                        )
                        AppText(
                            text = "Hubungi ULTKSP",
                            color = Color.White,
                            textStyle = Typography.labelLarge()
                        )
                    }
                }
            }
        }
    ) {
        val topPadding = it.calculateTopPadding()
        val bottomPadding = it.calculateBottomPadding()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding)
        ) {
            // Top section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight * 0.2).dp)
                    .background(
                        color = md_theme_light_primary,
                        shape = RoundedCornerShape(bottomStart = 48.dp, bottomEnd = 48.dp)
                    )
                    .padding(
                        top = topPadding,
                        start = 24.dp,
                        end = 24.dp
                    )
            ) {
                AppText(
                    text = "Pesan Darurat Terkirim",
                    textStyle = Typography.headlineSmall(),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                AppText(
                    text = "Pesan darurat telah terkirim ke kontak darurat kamu. Tetap tenang dan waspada! ",
                    textStyle = Typography.bodyMedium(),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Location
                Spacer(modifier = Modifier.height(8.dp))
                AppText(
                    text = "Lokasimu saat ini",
                    textStyle = Typography.titleLarge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
                AppText(
                    text = "Kamu berada di ${viewModel.userAddress.value} ",
                    textStyle = Typography.bodyMedium(),
                    color = Color.Gray,
                    maxLine = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 16.dp)
                )
                GoogleMap(
                    uiSettings = MapUiSettings(
                        compassEnabled = false,
                        myLocationButtonEnabled = false,
                        rotationGesturesEnabled = false,
                        scrollGesturesEnabled = false,
                        scrollGesturesEnabledDuringRotateOrZoom = false,
                        tiltGesturesEnabled = false,
                        zoomGesturesEnabled = false,
                        zoomControlsEnabled = false
                    ),
                    cameraPositionState = cameraPositionState,
                    properties = MapProperties(
                        isBuildingEnabled = true,
                        isIndoorEnabled = true,
                        maxZoomPreference = 30f,
                        minZoomPreference = 17.5f,
                        isMyLocationEnabled = true,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                        .height(160.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .shadow(elevation = 4.dp)
                ) {
                    Marker(
                        title = "Lokasi terkini saya",
                        state = userMarkerState,
                    )
                }

                // Emergency contact
                Spacer(modifier = Modifier.height(24.dp))
                AppText(
                    text = "Kontak Darurat",
                    textStyle = Typography.titleLarge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
                AppText(
                    text = "Kamu telah mengirim pesan darurat ke kontak berikut",
                    textStyle = Typography.bodyMedium(),
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(modifier = Modifier.padding(horizontal = 16.dp)) {
                    items(count = 3) {
                        ContactReceiverItem(
                            contactName = "Abim TIF UB-21",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }
            }
        }
    }
}