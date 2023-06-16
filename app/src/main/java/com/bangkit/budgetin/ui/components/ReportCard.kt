package com.bangkit.budgetin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bangkit.budgetin.ui.theme.Teal100

@Composable
fun ReportCard(
    productType: String?,
    productName: String?,
    currencyType: String?,
    price: String?,
    onProductTypeChange: (value: String) -> Unit = {},
    onProductNameChange: (value: String) -> Unit = {},
    onCurrencyTypeChange: (value: String) -> Unit = {},
    onPriceChange: (value: String) -> Unit = {},
    productList: List<String>,
    currencyList: List<String>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White),
        elevation = 2.dp,
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Dropdown(
                    modifier = Modifier.widthIn(max = 120.dp),
                    options = productList,
                    onValueChange = { onProductTypeChange(it) },
                    initialValue =
                    if(productType != null) productType
                    else {
                        onProductTypeChange(productList[0])
                        productList[0]
                    }
                )
                TextInput(
                    modifier = Modifier.fillMaxHeight(),
                    placeHolder = "Product",
                    placeHolderColor = Color.Gray,
                    value = productName ?: "",
                    onValueChange = { onProductNameChange(it) },
                    textFieldColors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Teal100,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = MaterialTheme.colors.background,
                        textColor = Color.Black
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Dropdown(
                    modifier = Modifier.widthIn(max = 120.dp),
                    options = currencyList,
                    onValueChange = { onCurrencyTypeChange(it) },
                    initialValue =
                    if(currencyType != null) currencyType
                    else {
                        onCurrencyTypeChange(currencyList[0])
                        currencyList[0]
                    }
                )
                TextInput(
                    modifier = Modifier.fillMaxHeight(),
                    placeHolder = "Price",
                    placeHolderColor = Color.Gray,
                    value = price ?: "",
                    onValueChange = { onPriceChange(it) },
                    textFieldColors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Teal100,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = MaterialTheme.colors.background,
                        textColor = Color.Black
                    )
                )
            }
        }
    }
}