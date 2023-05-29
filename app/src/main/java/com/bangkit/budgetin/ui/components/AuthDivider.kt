package com.bangkit.budgetin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun AuthDivider(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    ) {
        Divider(
            thickness = 2.dp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .alpha(0.5F)
                .align(Alignment.Center)
        )
        Text(
            text = text.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                else it.toString()
            },
            modifier = Modifier
                .align(Alignment.Center)
                .background(MaterialTheme.colors.background)
                .padding(horizontal = 8.dp),
            style = MaterialTheme.typography.body1
        )
    }
}