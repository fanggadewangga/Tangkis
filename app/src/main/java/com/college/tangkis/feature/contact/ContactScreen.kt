package com.college.tangkis.feature.contact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.feature.main.components.AppButton
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.components.ContactDeletionDialog
import com.college.tangkis.feature.main.components.EmergencyContactItem
import com.college.tangkis.feature.main.components.ErrorLayout
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(navController: NavController) {

    val viewModel = hiltViewModel<ContactViewModel>()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AppText(
                        text = "Kontak Darurat",
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
            AppButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
            ) {
                AppText(
                    text = "Tambah Kontak",
                    color = Color.White,
                    textStyle = Typography.labelLarge()
                )
            }
        }
    ) {
        val topPadding = it.calculateTopPadding()

        Box(modifier = Modifier.fillMaxSize()) {

            if (viewModel.isEmpty.value)
            // Show if contacts are empty
                ErrorLayout(
                    image = R.drawable.iv_empty_contact,
                    message = "Belum ada kontak darurat",
                    modifier = Modifier
                        .align(
                            Alignment.Center
                        )
                        .padding(bottom = it.calculateBottomPadding())
                )
            else
            // Contact item
                LazyColumn(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = topPadding + 16.dp,
                        end = 16.dp
                    )
                ) {
                    items(5) {
                        EmergencyContactItem(
                            name = "Abim TIF 21",
                            number = "+62 81234567890",
                            isDeletable = true,
                            modifier = Modifier.padding(top = 8.dp),
                            onDeleteClicked = { contactName ->
                                viewModel.apply {
                                    showDialog.value = true
                                    deleteContactName.value = contactName
                                }
                            }
                        )
                    }
                }

            // Alert
            AsyncImage(
                model = R.drawable.iv_contact_alert,
                contentDescription = "Contact alert",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = it.calculateBottomPadding() + 24.dp
                    )
                    .align(
                        Alignment.BottomCenter
                    )
            )
        }

        if (viewModel.showDialog.value)
            ContactDeletionDialog(
                contactName = viewModel.deleteContactName.value,
                setShowDialog = {
                    viewModel.showDialog.value = it
                },
                onCancelClicked = { viewModel.showDialog.value = false },
                onConfirmClicked = { /*TODO*/ },
            )
    }
}