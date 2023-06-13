package com.bangkit.budgetin.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.bangkit.budgetin.ui.theme.Teal100

@Composable
fun Dropdown(
    modifier: Modifier = Modifier,
    options: List<String>,
    initialValue: String,
    onValueChange: (String) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(initialValue) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Box(
        modifier = modifier
    ) {
        TextField(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .clip(RoundedCornerShape(8.dp))
                .onGloballyPositioned { coordinate ->
                    textFieldSize = coordinate.size.toSize()
                },
            textStyle = MaterialTheme.typography.h3,
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {
                selectedOptionText = it
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .rotate(if (expanded) 180F else 0F)
                        .clickable { expanded = !expanded })
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Teal100,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )
        DropdownMenu(
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .align(Alignment.BottomCenter),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.map {
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = it
                        onValueChange(it)
                        expanded = false
                    }
                ) {
                    Text(text = it)
                }
            }
        }
    }
}