package com.bangkit.budgetin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.bangkit.budgetin.R

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector,
    placeHolder: String,
    value: String,
    onValueChange: (value: String) -> Unit = {},
    isPassword: Boolean = false,
) {
    val isShow = rememberSaveable{ mutableStateOf(false) }
    val trailingIcon =
        painterResource(
            id = if (isShow.value) R.drawable.ic_visibility
            else R.drawable.ic_visibility_off
        )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
        ,
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = "$placeHolder icon",
                modifier = Modifier.padding(start = 8.dp)
            )
        },
        trailingIcon = {
            if(isPassword){
                Icon(
                    painter = trailingIcon,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ){
                        isShow.value = !isShow.value
                    }
                )
            }
        },
        placeholder = {
            Text(
                text = placeHolder,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.alpha(0.7F)
            )
        },
        textStyle = MaterialTheme.typography.subtitle1,
        visualTransformation = PasswordVisualTransformation()
    )
}