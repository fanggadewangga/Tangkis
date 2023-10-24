package com.college.tangkis.feature.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary
import com.college.tangkis.theme.md_theme_light_secondary

@Composable
fun ContactDeletionDialog(
    contactName: String,
    setShowDialog: (Boolean) -> Unit,
    onCancelClicked: () -> Unit,
    onConfirmClicked: () -> Unit,
    properties: DialogProperties = DialogProperties(),
) {
    Dialog(onDismissRequest = { setShowDialog(false) }, properties = properties) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            ) {
                AppText(text = "Yakin untuk menghapus", textStyle = Typography.titleSmall())
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppText(text = "nomor ", textStyle = Typography.titleSmall())
                    AppText(
                        text = contactName,
                        textStyle = Typography.titleSmall(),
                        color = md_theme_light_secondary
                    )
                }

                // Button
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    AppButton(
                        onClick = { onCancelClicked.invoke() },
                        backgroundColor = Color.White,
                        borderColor = md_theme_light_primary,
                        borderWidth = 1.dp,
                        modifier = Modifier.width(120.dp)
                    ) {
                        AppText(
                            text = "Batal",
                            textStyle = Typography.labelLarge(),
                            color = md_theme_light_primary
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    AppButton(
                        onClick = { onConfirmClicked.invoke() },
                        modifier = Modifier.width(120.dp)
                    ) {
                        AppText(
                            text = "Konfirmasi",
                            textStyle = Typography.labelLarge(),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}