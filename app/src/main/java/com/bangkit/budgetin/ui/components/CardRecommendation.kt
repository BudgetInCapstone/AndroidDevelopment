package com.bangkit.budgetin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bangkit.budgetin.R
import com.bangkit.budgetin.ui.theme.Teal400
import java.text.NumberFormat

@Composable
fun CardRecommendation(
    modifier: Modifier = Modifier,
    contentImage: Painter = painterResource(id = R.drawable.ic_food),
    name: String,
    address: String,
    priceMin: Long,
    priceMax: Long
){
    Row(
        modifier = modifier
            .width(300.dp)
            .widthIn(max = 350.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
    ) {
        Image(
            painter = contentImage,
            contentDescription = "menu image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
                .background(Teal400),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.secondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = address,
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Rp.${NumberFormat.getInstance().format(priceMin)} ~ ${NumberFormat.getInstance().format(priceMax)}",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onError
            )
        }
    }
}