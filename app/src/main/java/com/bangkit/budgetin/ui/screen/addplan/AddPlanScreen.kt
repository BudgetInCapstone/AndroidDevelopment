package com.bangkit.budgetin.ui.screen.addplan

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.budgetin.ui.components.ButtonApp
import com.bangkit.budgetin.ui.theme.BudgetInTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddPlanScreen(
    navigateToRecommendation: () -> Unit = {},
) {
    CreateSpendPlanContent(
        navigateToRecommendation = navigateToRecommendation
    )
}

@Composable
fun CreateSpendPlanContent(
    navigateToRecommendation: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.AccountCircle,
                                contentDescription = "Initial Income Icon",
                                modifier = Modifier.size(32.dp)
                            )
                            Text(
                                text = "Initial Income",
                                style = MaterialTheme.typography.h6,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Rp.",
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                            TextField(
                                value = "0",
                                onValueChange = { /* Handle budget input */ },
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(start = 8.dp),
                                textStyle = TextStyle(fontSize = 16.sp)
                            )
                        }
                    }
                }
                Text(
                    text = "Spend Plan",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                // Add code for rendering spent plan cards here
                repeat(3) {
                    SpendPlanCard()
                }
                FloatingActionButton(
                    onClick = { /* Handle adding a spent plan */ },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp, bottom = 32.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Spent Plan")
                }
            }
        }
        ButtonApp(
            text = "GENERATE SPEND PLAN",
            onClick = { navigateToRecommendation() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
fun SpendPlanCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = "Expense Icon",
                modifier = Modifier.size(32.dp)
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = "Food",
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = "Daily",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { /* Handle remove spend plan */ },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Remove Spend Plan",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddPlanScreenPreview() {
    BudgetInTheme {
        AddPlanScreen()
    }
}