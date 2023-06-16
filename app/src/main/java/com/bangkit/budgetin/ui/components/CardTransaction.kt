package com.bangkit.budgetin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bangkit.budgetin.R
import com.bangkit.budgetin.ui.theme.Teal100
import java.text.NumberFormat

@Composable
fun CardTransaction(
    modifier: Modifier = Modifier,
    type: Int = 1,
    date: String = "08 Mei 2023",
    price: Long = 10_000,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(16.dp)
            .width(100.dp)
            .widthIn(max = 120.dp)
    ) {
        Box(
            modifier = Modifier
                .size(45.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Teal100)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_food),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.Center)
            )
        }

        Text(
            text = when (type) {
                1 -> "Food"
                else -> ""
            },
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .padding(top = 8.dp)
        )
        Text(
            text = date,
            style = MaterialTheme.typography.body1,
            color = Color.Gray,
            modifier = Modifier
                .padding(top = 4.dp)
        )
        Text(
            text = "-Rp.${NumberFormat.getInstance().format(price)}",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.onError,
            textAlign = TextAlign.End,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
        )
    }
}