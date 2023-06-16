package com.bangkit.budgetin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun EmptyList(
    modifier: Modifier = Modifier,
    text: String = "",
    image: Painter
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp)
    ) {
        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
        )
        Text(
            text = text,
            color = Color.Black,
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(start = 16.dp)
        )
    }
}