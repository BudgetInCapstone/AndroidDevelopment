package com.bangkit.budgetin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun SignInBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    painter: Painter
){
    Box(
        modifier = modifier
            .size(60.dp)
            .shadow(4.dp, RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable {
                onClick()
            }
    ) {
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.Center)
        )
    }
}