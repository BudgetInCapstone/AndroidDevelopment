package com.bangkit.budgetin.ui.screen.addplan

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.budgetin.data.PlanCategory
import com.bangkit.budgetin.data.PlanType
import com.bangkit.budgetin.data.SpendPlan
import com.bangkit.budgetin.ui.components.ButtonApp
import com.bangkit.budgetin.ui.theme.BudgetInTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.draw.clip

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
    navigateToRecommendation: () -> Unit = {}
) {
    val budgetState = remember { mutableStateOf("") }
    val spendPlanList = remember { mutableStateListOf<SpendPlan>() }
    val expandedCategoryState = remember { mutableStateOf(false) }
    val expandedTypeState = remember { mutableStateOf(false) }
    val selectedCategoryState = remember { mutableStateOf(PlanCategory.Food) }
    val selectedTypeState = remember { mutableStateOf(PlanType.DAILY) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp, top = 32.dp, start = 16.dp, end = 16.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Rp",
                                modifier = Modifier.align(Alignment.CenterStart),
                                fontSize = 24.sp
                            )
                            // Editable TextField for budget input
                            TextField(
                                value = budgetState.value,
                                onValueChange = { budgetState.value = it },
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(start = 8.dp),
                                textStyle = TextStyle(fontSize = 20.sp),
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                            )
                        }
                    }
                }
                Text(
                    text = "Spend Plan",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                // Render spend plan cards
                spendPlanList.forEach { spendPlan ->
                    SpendPlanCard(
                        spendPlan = spendPlan,
                        onRemoveClick = { spendPlanList.remove(spendPlan) }
                    )
                }
                FloatingActionButton(
                    onClick = {
                        expandedCategoryState.value = true
                        expandedTypeState.value = false
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp, bottom = 32.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Spend Plan")
                }
                DropdownMenu(
                    expanded = expandedCategoryState.value,
                    onDismissRequest = { expandedCategoryState.value = false }
                ) {
                    DropdownMenuItem(
                        onClick = {
                            selectedCategoryState.value = PlanCategory.Food
                            expandedCategoryState.value = false
                            expandedTypeState.value = true
                        }
                    ) {
                        Text(text = "Food")
                    }
                    DropdownMenuItem(
                        onClick = {
                            selectedCategoryState.value = PlanCategory.Sport
                            expandedCategoryState.value = false
                            expandedTypeState.value = true
                        }
                    ) {
                        Text(text = "Sport")
                    }
                    DropdownMenuItem(
                        onClick = {
                            selectedCategoryState.value = PlanCategory.Shopping
                            expandedCategoryState.value = false
                            expandedTypeState.value = true
                        }
                    ) {
                        Text(text = "Shopping")
                    }
                    DropdownMenuItem(
                        onClick = {
                            selectedCategoryState.value = PlanCategory.Entertainment
                            expandedCategoryState.value = false
                            expandedTypeState.value = true
                        }
                    ) {
                        Text(text = "Entertainment")
                    }
                }
                if (expandedTypeState.value) {
                    DropdownMenu(
                        expanded = expandedTypeState.value,
                        onDismissRequest = { expandedTypeState.value = false }
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                selectedTypeState.value = PlanType.DAILY
                                expandedTypeState.value = false
                                spendPlanList.add(
                                    SpendPlan(
                                        category = selectedCategoryState.value,
                                        type = selectedTypeState.value
                                    )
                                )
                            }
                        ) {
                            Text(text = "Daily")
                        }
                        DropdownMenuItem(
                            onClick = {
                                selectedTypeState.value = PlanType.WEEKLY
                                expandedTypeState.value = false
                                spendPlanList.add(
                                    SpendPlan(
                                        category = selectedCategoryState.value,
                                        type = selectedTypeState.value
                                    )
                                )
                            }
                        ) {
                            Text(text = "Weekly")
                        }
                        DropdownMenuItem(
                            onClick = {
                                selectedTypeState.value = PlanType.MONTHLY
                                expandedTypeState.value = false
                                spendPlanList.add(
                                    SpendPlan(
                                        category = selectedCategoryState.value,
                                        type = selectedTypeState.value
                                    )
                                )
                            }
                        ) {
                            Text(text = "Monthly")
                        }
                    }
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
fun SpendPlanCard(
    spendPlan: SpendPlan,
    onRemoveClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color(0xFFE8EAF6),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = spendPlan.category.toString(),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.primary
                )
                if (spendPlan.expanded) {
                    DropdownMenu(
                        expanded = spendPlan.expanded,
                        onDismissRequest = { spendPlan.expanded = false }
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                spendPlan.type = PlanType.DAILY
                                spendPlan.expanded = false
                            }
                        ) {
                            Text(text = "Daily")
                        }
                        DropdownMenuItem(
                            onClick = {
                                spendPlan.type = PlanType.WEEKLY
                                spendPlan.expanded = false
                            }
                        ) {
                            Text(text = "Weekly")
                        }
                        DropdownMenuItem(
                            onClick = {
                                spendPlan.type = PlanType.MONTHLY
                                spendPlan.expanded = false
                            }
                        ) {
                            Text(text = "Monthly")
                        }
                    }
                }
                Text(
                    text = when (spendPlan.type) {
                        PlanType.DAILY -> "Daily"
                        PlanType.WEEKLY -> "Weekly"
                        PlanType.MONTHLY -> "Monthly"
                    },
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = onRemoveClick,
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