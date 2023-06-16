package com.bangkit.budgetin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.bangkit.budgetin.ui.theme.DarkGreen800
import com.bangkit.budgetin.ui.theme.Teal400
import com.bangkit.budgetin.ui.theme.Teal800
import java.text.NumberFormat

@Composable
fun CardBalance(
    modifier: Modifier = Modifier,
    percent: Float = 1F,
    balance: Long = 3000000,
    onClick: () -> Unit = {}
) {
    var boxWidth by remember {
        mutableStateOf(1F)
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .onGloballyPositioned {
                boxWidth = it.size.width.toFloat()
            }
            .background(
                brush = Brush.radialGradient(
                    0.0F to Teal400,
                    0.6F to Teal800,
                    1F to DarkGreen800,
                    center = Offset(x = boxWidth, y = 0F),
                    radius = (boxWidth * 8.5F / 10)
                )
            )
            .clickable { onClick() }
            .padding(24.dp)
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
        ) {
            CircularProgressIndicator(
                progress = percent,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxSize()
                    .align(Alignment.TopStart),
                backgroundColor = MaterialTheme.colors.primary,
                strokeWidth = 4.dp,
                color = Color.White
            )
            Text(
                text = "${(percent * 100).toInt()}%",
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .align(Alignment.Center)
                    .zIndex(1F),
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "Remainer Balance",
                style = MaterialTheme.typography.subtitle1,
                color = Color.White,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = "Rp.${NumberFormat.getInstance().format(balance)}",
                style = MaterialTheme.typography.h1,
                fontSize = 28.sp,
                color = Color.White
            )
        }
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "notification",
            tint = Color.White,
            modifier = Modifier
                .size(28.dp)
                .align(Alignment.TopEnd)
        )
    }
}