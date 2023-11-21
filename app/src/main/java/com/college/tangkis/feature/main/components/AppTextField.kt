package com.college.tangkis.feature.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.college.tangkis.R
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    contentSpacing: Dp = 4.dp,
    showWarningMessage: Boolean = false,
    warningMessage: String = "Field tidak boleh kosong!",
    placeHolder: String,
    label: String,
    textStyle: TextStyle = Typography.bodyLarge(),
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    shape: Shape = RectangleShape,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    textColor: Color = Color.Black,
    placeHolderColor: Color = Gray,
    disablePlaceHolderColor: Color = Gray,
    unfocusedLabelColor: Color = Gray,
    focusedLabelColor: Color = md_theme_light_primary,
    disabledTextColor: Color = Gray,
    backgroundColor: Color = Color.White,
    cursorColor: Color = Color.Black,
    errorCursorColor: Color = Color.Red,
    focusedIndicatorColor: Color = md_theme_light_primary,
    unfocusedIndicatorColor: Color = Gray,
    disabledIndicatorColor: Color = Gray,
    errorIndicatorColor: Color = Color.Red,
) {
    Column(verticalArrangement = Arrangement.spacedBy(contentSpacing)) {
        if (isPassword) {
            val passwordVisibility = remember { mutableStateOf(false) }
            val icon = if (passwordVisibility.value)
                painterResource(id = R.drawable.ic_password_visibility_on)
            else
                painterResource(id = R.drawable.ic_password_visibility_off)

            OutlinedTextField(
                modifier = modifier,
                value = value,
                onValueChange = onValueChange,
                shape = shape,
                readOnly = readOnly,
                leadingIcon = leadingIcon,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }
                    ) {
                        Icon(painter = icon, contentDescription = "Visibility Icon")
                    }
                },
                isError = isError,
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                maxLines = maxLines,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = textColor,
                    disabledTextColor = disabledTextColor,
                    containerColor = backgroundColor,
                    cursorColor = cursorColor,
                    errorCursorColor = errorCursorColor,
                    focusedIndicatorColor = focusedIndicatorColor,
                    unfocusedIndicatorColor = unfocusedIndicatorColor,
                    disabledIndicatorColor = disabledIndicatorColor,
                    focusedLabelColor = focusedLabelColor,
                    unfocusedLabelColor = unfocusedLabelColor,
                    placeholderColor = placeHolderColor,
                    errorIndicatorColor = errorIndicatorColor
                ),
                enabled = enabled,
                textStyle = textStyle,
                label = {
                    Text(
                        text = label,
                    )
                },
                placeholder = {
                    Text(
                        text = placeHolder,
                    )
                }
            )
        } else {
            OutlinedTextField(
                modifier = modifier,
                value = value,
                onValueChange = onValueChange,
                shape = shape,
                readOnly = readOnly,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                isError = isError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                maxLines = maxLines,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = textColor,
                    disabledTextColor = disabledTextColor,
                    containerColor = backgroundColor,
                    cursorColor = cursorColor,
                    errorCursorColor = errorCursorColor,
                    focusedIndicatorColor = focusedIndicatorColor,
                    unfocusedIndicatorColor = unfocusedIndicatorColor,
                    disabledIndicatorColor = disabledIndicatorColor,
                    focusedLabelColor = focusedLabelColor,
                    unfocusedLabelColor = unfocusedLabelColor,
                    placeholderColor = placeHolderColor,
                    errorIndicatorColor = errorIndicatorColor
                ),
                enabled = enabled,
                textStyle = textStyle,
                label = {
                    Text(
                        text = label,
                    )
                },
                placeholder = {
                    Text(
                        text = placeHolder,
                    )
                }
            )
        }

        if (showWarningMessage) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppText(
                    text = warningMessage,
                    color = md_theme_light_primary,
                    textStyle = Typography.bodySmall()
                )
            }
        }
    }
}

@Composable
fun AppBasicTextField(
    modifier: Modifier = Modifier,
    contentSpacing: Dp = 4.dp,
    textFieldHeight: Dp = 56.dp,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    showWarningMessage: Boolean = false,
    warningMessage: String = "",
    borderColor: Color = LightGray,
    placeHolder: String,
    textStyle: TextStyle = Typography.bodyMedium(),
    value: String,
    onValueChange: (String) -> Unit,
    shape: Shape = RoundedCornerShape(100.dp),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    isWithBorder: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    placeHolderColor: Color = Gray,
    disablePlaceHolderColor: Color = Gray,
    backgroundColor: Color = if (enabled) Color.White else Gray,

    ) {
    Column(verticalArrangement = Arrangement.spacedBy(contentSpacing)) {
        val customModifier: Modifier = if (isWithBorder) modifier
            .background(color = backgroundColor, shape = shape)
            .height(textFieldHeight)
            .border(
                width = 1.dp,
                color = if (isError) Color.Red else borderColor,
                shape = shape
            ) else
            modifier
                .background(color = backgroundColor, shape = shape)
                .height(textFieldHeight)

        BasicTextField(
            modifier = customModifier,
            value = value,
            onValueChange = onValueChange,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            textStyle = textStyle,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = verticalAlignment,
                    modifier = modifier
                        .height(textFieldHeight)
                        .padding(12.dp),
                ) {
                    if (leadingIcon != null) leadingIcon()
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        if (value.isEmpty())
                            AppText(
                                text = placeHolder,
                                color = if (enabled) placeHolderColor else disablePlaceHolderColor,
                                textStyle = Typography.bodyLarge(),
                            )
                        innerTextField()
                    }
                    if (trailingIcon != null) trailingIcon()
                }
            },
            enabled = enabled,
            readOnly = readOnly,
            maxLines = Int.MAX_VALUE,
            visualTransformation = visualTransformation,
            cursorBrush = SolidColor(Color.Black)
        )

        if (showWarningMessage) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(imageVector = Icons.Default.Warning, contentDescription = "Warning Icon")
                AppText(
                    text = warningMessage,
                    color = when {
                        isError -> Color.Red
                        else -> Gray
                    }
                )
            }
        }
    }
}