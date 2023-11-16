package com.college.tangkis.feature.main.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.data.source.remote.model.response.contact.ContactResponse
import com.college.tangkis.theme.Typography

@Composable
fun EmergencyContactItem(
    modifier: Modifier = Modifier,
    contact: com.college.tangkis.data.source.remote.model.response.contact.ContactResponse,
    isDeletable: Boolean = false,
    onDeleteClicked: ((String, String) -> Unit)? = null
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {

            // Left section
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = R.drawable.iv_contact_icon,
                    contentDescription = "Contact icon",
                    modifier = Modifier.size(38.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    AppText(text = contact.name, textStyle = Typography.labelLarge())
                    AppText(text = contact.number, textStyle = Typography.bodySmall(), color = Color.Gray)
                }
            }

            // Delete button
            if (isDeletable)
                AsyncImage(
                    model = R.drawable.ic_delete,
                    contentDescription = "Delete icon",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            onDeleteClicked?.invoke(contact.contactId, contact.name)
                        }
                )
        }
    }
}