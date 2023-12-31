package com.college.tangkis.feature.home

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.data.Resource
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.components.EmergencyContactItem
import com.college.tangkis.feature.main.components.EmergencyContactItemShimmer
import com.college.tangkis.feature.main.components.HomeArticleItem
import com.college.tangkis.feature.main.components.HomeArticleItemShimmer
import com.college.tangkis.feature.main.components.ServiceItem
import com.college.tangkis.feature.main.navigation.BottomNavigationBar
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.feature.main.utils.getCurrentLocation
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary
import com.college.tangkis.theme.md_theme_light_secondary
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(navController: NavController) {

    val viewModel = hiltViewModel<HomeViewModel>()
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current
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
                }
            }
        }
    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ),
        -> {
            viewModel.isPermissionGranted.value = true
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
    val userState = viewModel.userState.collectAsStateWithLifecycle()
    val contactState = viewModel.contactState.collectAsStateWithLifecycle()
    val articleState = viewModel.articleState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        systemUiController.setStatusBarColor(color = md_theme_light_primary)
        viewModel.getContacts()
    }

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

        Surface(
            color = md_theme_light_primary,
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = bottomPadding)
            ) {

                // Top section
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        // username
                        AppText(
                            text = if (userState.value is Resource.Success) "Selamat datang ${userState.value.data!!.name}" else "Username",
                            textStyle = Typography.titleMedium(),
                            color = Color.White,
                            maxLine = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.placeholder(
                                visible = userState.value is Resource.Loading,
                                color = Color.LightGray,
                                highlight = PlaceholderHighlight
                                    .shimmer(highlightColor = Color.White),
                                shape = RoundedCornerShape(8.dp)
                            )
                        )

                        // location
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = R.drawable.ic_location_marker,
                                contentDescription = "Location marker",
                                colorFilter = ColorFilter.tint(color = Color.White),
                                modifier = Modifier.size(21.dp)
                            )
                            AppText(
                                text = viewModel.userAddress.value,
                                textStyle = Typography.bodyMedium(),
                                color = Color.White,
                                maxLine = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }

                // White card section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((screenHeight * 0.8).dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                        )
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {

                    // Univ Service
                    AppText(text = "Layanan Di Kampusmu", textStyle = Typography.titleLarge())
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    ) {
                        ServiceItem(
                            serviceName = "Pelaporan \nOnline",
                            icon = R.drawable.ic_report,
                            modifier = Modifier
                                .width((screenWidth * 0.44).dp)
                                .height(80.dp)
                        ) {
                            navController.navigate(Screen.Report.route)
                        }
                        ServiceItem(
                            serviceName = "Konsultasi",
                            icon = R.drawable.ic_consult,
                            modifier = Modifier
                                .width((screenWidth * 0.44).dp)
                                .height(80.dp)
                        ) {
                            navController.navigate(Screen.Consultation.route)
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AppText(text = "Kontak Darurat", textStyle = Typography.titleLarge())
                        AppText(
                            text = "Lihat semua",
                            color = md_theme_light_secondary,
                            textStyle = Typography.labelLarge(),
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.Contact.route)
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    AppText(
                        text = "Tambahkan kontak darurat untuk berjaga-jaga",
                        textStyle = Typography.bodyMedium(),
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    if (contactState.value is Resource.Loading)
                        repeat(5) {
                            EmergencyContactItemShimmer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            )
                        }
                    else if (contactState.value is Resource.Success)
                        contactState.value.data!!.forEach { contact ->
                            EmergencyContactItem(
                                contact = contact,
                                isDeletable = false,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            )
                        }

                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AppText(text = "Artikel Informasi", textStyle = Typography.titleLarge())
                        AppText(
                            text = "Lihat semua",
                            color = md_theme_light_secondary,
                            textStyle = Typography.labelLarge(),
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.Article.route)
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    AppText(
                        text = "Bacalah artikel informasi sebagai panduan penggunaan Tangkis dan informasi layanan yang tersedia di fakultasmu",
                        textStyle = Typography.bodyMedium(),
                        color = Color.Gray,
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow {
                        if (articleState.value is Resource.Loading)
                            items(3) {
                                HomeArticleItemShimmer(modifier = Modifier.padding(end = 16.dp))
                            }
                        else if (articleState.value is Resource.Success)
                            items(articleState.value.data!!) { article ->
                                HomeArticleItem(
                                    article = article,
                                    onClick = {
                                        navController.navigate(
                                            Screen.ArticleDetail.route.replace(
                                                oldValue = "{articleId}",
                                                newValue = article.articleId
                                            )
                                        )
                                    },
                                    modifier = Modifier.padding(end = 16.dp)
                                )
                            }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}