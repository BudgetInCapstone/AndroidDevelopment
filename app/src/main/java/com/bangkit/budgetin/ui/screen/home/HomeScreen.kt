package com.bangkit.budgetin.ui.screen.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.budgetin.R
import com.bangkit.budgetin.ui.components.*
import com.bangkit.budgetin.ui.theme.*

@Composable
fun HomeScreen(
    navigateToAddPlan: () -> Unit = {},
    navigateToRecommendation: () -> Unit = {},
) {
    // TODO: Cek apakah user punya plan
    val isHavePlan = false
    if (isHavePlan)
        HomeContent(
            navigateToRecommendation = navigateToRecommendation
        )
    else
        HomeNoContent(
            navigateToAddPlan = navigateToAddPlan
        )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    navigateToRecommendation: () -> Unit = {},
) {
    // TODO: Diganti data dari API
    val transactionList = listOf(1, 2, 3, 4, 5, 6, 7)
    val recommendationList = listOf(1, 2, 3, 4)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
            .padding(bottom = 16.dp)
    ) {
        CardBalance(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            percent = 0.5F,
            onClick = {navigateToRecommendation()}
        )

        DailySpend(
            dailySpend = 20_000,
            dailyMax = 30_000
        )

        HeadingNavigation(
            headingText = "Last Transaction",
            navigationText = "Detail"
        )

        if (transactionList.isNotEmpty()) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                items(transactionList, { it }) {
                    CardTransaction(
                        date = "0$it Juni 2023",
                        price = (it * 10_000).toLong()
                    )
                }
            }
        } else {
            EmptyList(
                text = "You Haven’t Made Any Transaction",
                image = painterResource(id = R.drawable.ic_empty_transaction)
            )
        }

        // Your Recommendation
        HeadingNavigation(
            headingText = "Your Recommendation",
            navigationText = "Detail"
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items(recommendationList, { it }) {
                CardRecommendation(
                    name = "Bakso Pak Udin $it",
                    address = "Jl. Soekarno Hatta, Blok C1 no 15",
                    priceMin = 25_000,
                    priceMax = 35_000
                )
            }
        }

    }
}

@Composable
fun HomeNoContent(
    modifier: Modifier = Modifier,
    navigateToAddPlan: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .padding(bottom = 16.dp)
    ) {
        CardBalanceNoPlan(
            onClick = { navigateToAddPlan() }
        )
        Text(
            text = "Last Transactions",
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(top = 32.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_empty_plan),
            contentDescription = "empty plan",
            modifier = Modifier
                .size(150.dp)
                .padding(top = 24.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "You Haven't Make a Plan",
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_2_XL)
@Composable
fun HomePreview() {
    BudgetInTheme {
        HomeContent(
            modifier = Modifier.fillMaxSize()
        )
    }
}

//@Preview(showBackground = true, device = Devices.PIXEL_2_XL)
//@Composable
//fun HomeNoContentPreview() {
//    BudgetInTheme {
//        HomeNoContent(
//            modifier = Modifier.fillMaxSize()
//        )
//    }
//}