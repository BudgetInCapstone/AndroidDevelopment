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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bangkit.budgetin.R

@Composable
fun TransactionCard(
    modifier: Modifier = Modifier,
    menuName: String = "",
    menuType: String = "",
    menuImage: Painter = painterResource(id = R.drawable.menu1),
    menuPrice: Long = 0,
    transactionDate: String = ""
){
    Row(
        modifier = modifier
            .height(90.dp)
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight(),
            painter = menuImage,
            contentDescription = "menu image",
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1F),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = menuName,
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(bottom = 4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = menuType,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(bottom = 2.dp),
                color = MaterialTheme.colors.secondary
            )
            Text(
                text = transactionDate,
                style = MaterialTheme.typography.body1,
                color = Color.Gray
            )
        }

        Text(
            text = "Rp.$menuPrice",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.error,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}