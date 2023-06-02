package com.bangkit.budgetin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.bangkit.budgetin.ui.theme.Teal200
import com.bangkit.budgetin.ui.theme.Teal400
import com.bangkit.budgetin.ui.theme.Teal800
import java.text.NumberFormat

@Composable
fun DailySpend(
    modifier: Modifier = Modifier,
    dailySpend: Long,
    dailyMax: Long,
) {
    var progressBarWidth by remember {
        mutableStateOf(1F)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 24.dp, end = 24.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp)

    ) {

        Text(
            text = "Spend Today",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${(dailySpend.toFloat() / dailyMax.toFloat() * 100).toInt()}%",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "Rp.${NumberFormat.getInstance().format(dailySpend)}/${NumberFormat.getInstance().format(dailyMax)}",
                style = MaterialTheme.typography.body1
            )
        }

        // Progress bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    progressBarWidth = it.size.width.toFloat()
                }
                .height(10.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(
                    brush = Brush.linearGradient(
                        0F to Teal800,
                        0.5F to Teal400,
                        1F to Teal200,
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(
                        with(LocalDensity.current) {
                            (progressBarWidth * (1 - dailySpend.toFloat() / dailyMax)).toDp()
                        }
                    )
                    .align(Alignment.CenterEnd)
                    .zIndex(1F)
                    .background(MaterialTheme.colors.background),
            )
        }
    }
}