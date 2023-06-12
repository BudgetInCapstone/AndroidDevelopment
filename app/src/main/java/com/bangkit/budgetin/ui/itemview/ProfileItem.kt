package com.bangkit.budgetin.ui.itemview

import androidx.compose.ui.graphics.painter.Painter

data class ProfileItem(
    val title: String,
    val description: String? = null,
    val image: Painter
)