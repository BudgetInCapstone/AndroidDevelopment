package com.bangkit.budgetin.ui.screen.spend

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.budgetin.ui.ViewModelFactory
import com.bangkit.budgetin.ui.components.ButtonApp
import com.bangkit.budgetin.ui.components.ReportCard
import com.bangkit.budgetin.ui.theme.BudgetInTheme

@Composable
fun SpendScreen() {
    SpendContent()
}

@Composable
fun SpendContent(
    modifier: Modifier = Modifier,
    spendViewModel: SpendViewModel = viewModel(
        factory = ViewModelFactory()
    ),
) {
    val reportForm = spendViewModel.reportPost
    var buttonHeight by remember { mutableStateOf(Size.Zero) }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    // 48.dp = padding button + padding column
                    bottom = with(LocalDensity.current) { buttonHeight.height.toDp() + 48.dp }
                )
                .padding(horizontal = 24.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            reportForm.mapIndexed { index, reportItem ->
                ReportCard(
                    productType = reportItem.productType,
                    productName = reportItem.productName,
                    currencyType = reportItem.currencyType,
                    price = reportItem.price,
                    productList = listOf("Food", "Sport", "Shopping"),
                    currencyList = listOf("Rp", "$"),
                    onProductTypeChange = {
                        spendViewModel.updateReport(
                            reportItem.copy(productType = it),
                            index
                        )
                    },
                    onProductNameChange = {
                        spendViewModel.updateReport(
                            reportItem.copy(productName = it),
                            index
                        )
                    },
                    onCurrencyTypeChange = {
                        spendViewModel.updateReport(
                            reportItem.copy(currencyType = it),
                            index
                        )
                    },
                    onPriceChange = {
                        spendViewModel.updateReport(
                            reportItem.copy(price = it),
                            index
                        )
                    },
                )
            }

            IconButton(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.secondary),
                onClick = { spendViewModel.addReport() }
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
        ButtonApp(
            text = "bayar",
            onClick = {},
            modifier = Modifier
                .padding(bottom = 24.dp)
                .padding(horizontal = 24.dp)
                .align(Alignment.BottomCenter)
                .onGloballyPositioned { coordinate ->
                    buttonHeight = coordinate.size.toSize()
                },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SpendPreview() {
    BudgetInTheme() {
        SpendContent(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        )
    }
}