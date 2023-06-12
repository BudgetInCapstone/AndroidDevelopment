package com.bangkit.budgetin.ui.screen.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.budgetin.R
import com.bangkit.budgetin.ui.components.Dropdown
import com.bangkit.budgetin.ui.components.TransactionCard
import com.bangkit.budgetin.ui.theme.BudgetInTheme

@Composable
fun TransactionScreen() {
    TransactionContent()
}

@Composable
fun TransactionContent(
    modifier: Modifier = Modifier,
) {
    val menuList = remember { mutableStateListOf(
        "Bakso malang",
        "Mie ayam",
        "Nasi goreng"
    ) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Dropdown(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 8.dp),
            options = listOf("Menu Asc", "Menu Desc", "Date Asc", "Date Desc"),
            initialValue = "Sort",
            onValueChange = { }
        )

        menuList.forEach {
            TransactionCard(
                menuName = it,
                menuType = "Food",
                menuPrice = 30_000,
                menuImage = painterResource(id = R.drawable.menu1),
                transactionDate = "02 Juni 2023"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionPreview() {
    BudgetInTheme {
        TransactionContent(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        )
    }
}
