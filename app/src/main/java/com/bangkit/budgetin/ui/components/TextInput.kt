package com.bangkit.budgetin.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.bangkit.budgetin.R

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    placeHolder: String,
    placeHolderColor: Color = MaterialTheme.colors.secondary,
    value: String,
    onValueChange: (value: String) -> Unit = { },
    isPassword: Boolean = false,
    textFieldColors: TextFieldColors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
    inputValidation: (value: String) -> String? = { null },
) {
    var errorMessage by remember { mutableStateOf<String?>("") }
    val isShow = rememberSaveable { mutableStateOf(false) }
    val trailingIcon =
        painterResource(
            id = if (isShow.value) R.drawable.ic_visibility
            else R.drawable.ic_visibility_off
        )

    val density = LocalDensity.current

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                errorMessage = inputValidation(it)
            },
            isError = !errorMessage.isNullOrEmpty(),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
            shape = RoundedCornerShape(8.dp),
            colors = textFieldColors,
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = "$placeHolder icon",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            } else null,
            trailingIcon = if (isPassword) {
                {
                    Icon(
                        painter = trailingIcon,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                isShow.value = !isShow.value
                            }
                    )
                }
            } else null,
            placeholder = {
                Text(
                    text = placeHolder,
                    color = placeHolderColor,
                    modifier = Modifier.alpha(0.7F)
                )
            },
            textStyle = MaterialTheme.typography.subtitle1,
            visualTransformation = if (isShow.value || !isPassword)
                VisualTransformation.None else
                PasswordVisualTransformation(),
        )
        AnimatedVisibility(
            visible = !errorMessage.isNullOrEmpty(),
            enter = slideInVertically {
                with(density) { -20.dp.roundToPx() }
            } + expandVertically(
                expandFrom = Alignment.Top
            ) + fadeIn(
                initialAlpha = 0F
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}