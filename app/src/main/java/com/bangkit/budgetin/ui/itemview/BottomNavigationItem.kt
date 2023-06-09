package com.bangkit.budgetin.ui.itemview

import androidx.compose.ui.graphics.painter.Painter
import com.bangkit.budgetin.ui.navigation.Screen

data class BottomNavigationItem(
    val title: String,
    val icon: Painter,
    val screen: Screen
)
