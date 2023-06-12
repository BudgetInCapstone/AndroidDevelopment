package com.bangkit.budgetin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.budgetin.ui.theme.*

@Composable
fun CardBalanceNoPlan(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    var boxWidth by remember {
        mutableStateOf(1F)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .onGloballyPositioned {
                boxWidth = it.size.width.toFloat()
            }
            .background(
                color = Gray400
            )
            .clickable { onClick() }
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1F)
            ) {
                Text(
                    text = "Create New Plan",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White
                )
                Text(
                    text = "Spend Plan is Empty",
                    style = MaterialTheme.typography.h1,
                    fontSize = 32.sp,
                    color = Color.White
                )
            }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Gray800)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "create new plan",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}