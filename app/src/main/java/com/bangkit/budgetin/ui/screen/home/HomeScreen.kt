package com.bangkit.budgetin.ui.screen.home

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bangkit.budgetin.ui.theme.BudgetInTheme

@Composable
fun HomeScreen(
) {
    HomeContent()
}

@Composable
fun HomeContent(
) {
    Text(text = "AAAAAA")
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    BudgetInTheme {
        HomeContent()
    }
}