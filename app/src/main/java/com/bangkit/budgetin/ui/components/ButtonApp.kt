package com.bangkit.budgetin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonApp(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colors.primary,
    onClick: () -> Unit = {},
){
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding()
            .clip(RoundedCornerShape(8.dp)),
        colors = ButtonDefaults.buttonColors(color)
    ) {
        Text(
            text = text.uppercase(),
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.button
        )
    }
}