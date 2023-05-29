package com.bangkit.budgetin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.budgetin.R

@Composable
fun AuthHeader(
    modifier: Modifier = Modifier,
    text: String = ""
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(MaterialTheme.colors.primary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_header),
            contentDescription = "background",
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.TopEnd),
            contentScale = ContentScale.Crop
        )
        Text(
            text = text,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onPrimary,
            fontSize = 36.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}