package com.bangkit.budgetin.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeadingNavigation(
    modifier: Modifier = Modifier,
    headingText: String = "",
    navigationText: String = "Detail",
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 24.dp, end = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = headingText,
            style = MaterialTheme.typography.h1
        )
        Text(
            text = "$navigationText >>",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.secondary
        )
    }
}