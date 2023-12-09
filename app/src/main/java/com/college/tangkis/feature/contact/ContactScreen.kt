package com.college.tangkis.feature.contact

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.data.Resource
import com.college.tangkis.feature.main.components.AppButton
import com.college.tangkis.feature.main.components.AppDialog
import com.college.tangkis.feature.main.components.AppSearchField
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.components.EmergencyContactItem
import com.college.tangkis.feature.main.components.EmergencyContactItemShimmer
import com.college.tangkis.feature.main.components.ErrorLayout
import com.college.tangkis.feature.main.components.LocalContactItem
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary
import com.college.tangkis.theme.md_theme_light_primaryContainer
import com.college.tangkis.theme.md_theme_light_secondary
import es.dmoral.toasty.Toasty

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ContactScreen(navController: NavController) {

    val viewModel = hiltViewModel<ContactViewModel>()
    val context = LocalContext.current
    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            viewModel.isPermissionGranted.value = isGranted
        }
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        viewModel.apply {
            isPermissionGranted.value = true
        }
    } else {
        SideEffect {
            permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
        }
    }
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = viewModel.sheetState.value,
        confirmValueChange = { false },
        skipHalfExpanded = true
    )

    val screenHeight = LocalConfiguration.current.screenHeightDp
    val contactState = viewModel.getContactState.collectAsStateWithLifecycle()
    val deleteContactState = viewModel.deleteContactState.collectAsStateWithLifecycle()
    val addContactState = viewModel.addContactState.collectAsStateWithLifecycle()

    LaunchedEffect(deleteContactState.value) {
        when (deleteContactState.value) {
            is Resource.Success -> Toasty.success(
                context,
                "Berhasil menghapus kontak!",
                Toast.LENGTH_SHORT
            ).show()

            is Resource.Error -> Toasty.error(
                context,
                "Gagal menghapus kontak!",
                Toast.LENGTH_SHORT
            ).show()

            else -> {}
        }
        viewModel.getContacts()
    }

    LaunchedEffect(viewModel.isPermissionGranted.value) {
        if (viewModel.isPermissionGranted.value)
            viewModel.loadDeviceContacts(context)
    }

    LaunchedEffect(addContactState.value) {
        when (addContactState.value) {
            is Resource.Success -> Toasty.success(
                context,
                "Berhasil menambah kontak!",
                Toast.LENGTH_SHORT
            ).show()

            is Resource.Error -> Toasty.error(context, "Gagal menambah kontak!", Toast.LENGTH_SHORT)
                .show()

            else -> {}
        }
        viewModel.getContacts()
    }

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetGesturesEnabled = false,
        sheetShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        sheetContent = {
            Box(
                modifier = Modifier
                    .height((screenHeight * 0.95).dp)
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                    )
            ) {
                Column(
                    modifier = Modifier.height((screenHeight * 0.9).dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    AppSearchField(
                        valueState = viewModel.query.value,
                        borderColor = md_theme_light_primaryContainer,
                        placeholder = "Cari Kontak",
                        onValueChange = {
                            viewModel.apply {
                                query.value = it
                                getSavedContacts(viewModel.query.value)
                            }
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.Black
                            )
                        },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    LazyColumn(
                        modifier = Modifier
                            .height((screenHeight * 0.75).dp)
                            .padding(start = 8.dp, top = 24.dp, end = 8.dp)
                    ) {
                        items(viewModel.savedDeviceContacts) {
                            LocalContactItem(
                                contact = it,
                                onSelected = { contact ->
                                    if (viewModel.listOfSelectedContact.size <= 4)
                                        viewModel.listOfSelectedContact.add(contact)
                                    else
                                        Toasty.error(
                                            context,
                                            "Hanya dapat menambahkan 5 kontak",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                },
                                onDeselected = { contact ->
                                    viewModel.listOfSelectedContact.remove(contact)
                                },
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                    AppButton(
                        onClick = {
                            viewModel.apply {
                                sheetState.value = ModalBottomSheetValue.Hidden
                                addContacts()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        AppText(text = "Selesai", color = Color.White)
                    }
                }

            }
        },
    ) {
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
                    onClick = {
                        if (viewModel.isPermissionGranted.value) {
                            viewModel.sheetState.value = ModalBottomSheetValue.Expanded
                            viewModel.getSavedContacts()
                        }
                        else permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
                    },
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

                when (contactState.value) {
                    is Resource.Loading -> {
                        LazyColumn(
                            modifier = Modifier.padding(
                                start = 16.dp,
                                top = topPadding + 16.dp,
                                end = 16.dp
                            )
                        ) {
                            items(5) {
                                EmergencyContactItemShimmer(modifier = Modifier.padding(top = 8.dp))
                            }
                        }
                    }

                    is Resource.Success -> {
                        if (contactState.value.data!!.isEmpty())
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
                                modifier = Modifier
                                    .padding(
                                        start = 16.dp,
                                        top = topPadding + 16.dp,
                                        end = 16.dp
                                    )
                                    .background(Color.White)
                            ) {
                                if (contactState.value is Resource.Success)
                                    items(contactState.value.data!!, key = { contact ->
                                        contact.number
                                    }) { contact ->
                                        EmergencyContactItem(
                                            contact = contact,
                                            isDeletable = true,
                                            modifier = Modifier.padding(top = 8.dp)
                                        ) { id, name ->
                                            viewModel.apply {
                                                showDialog.value = true
                                                deleteContactId.value = id
                                                deleteContactName.value = name
                                            }
                                        }
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

                    else -> {}
                }
            }

            if (viewModel.showDialog.value)
                AppDialog(
                    dialogContent = {
                        AppText(text = "Yakin untuk menghapus", textStyle = Typography.titleSmall())
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            AppText(text = "nomor ", textStyle = Typography.titleSmall())
                            AppText(
                                text = viewModel.deleteContactName.value,
                                textStyle = Typography.titleSmall(),
                                color = md_theme_light_secondary
                            )
                        }
                    },
                    setShowDialog = { isShow ->
                        viewModel.showDialog.value = isShow
                    },
                    onCancelClicked = { viewModel.showDialog.value = false },
                    onConfirmClicked = {
                        viewModel.apply {
                            deleteContact()
                            showDialog.value = false
                        }
                    },
                )
        }
    }
}