package com.bangkit.budgetin.ui.screen.recommend

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.bangkit.budgetin.R
import com.bangkit.budgetin.ui.components.ButtonApp
import com.bangkit.budgetin.ui.components.ReportCard
import com.bangkit.budgetin.ui.tabs.TabItem
import com.bangkit.budgetin.ui.theme.BudgetInTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

//@OptIn(ExperimentalPagerApi::class)
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun RecommendedPlanScreen(
//    navigateToHome: () -> Unit = {}
//) {
//    Scaffold(
//        content = {
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                // Card section
//                CardSection()
//
//                // Tab section
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Center
//                ) {
////                    TabSection()
//                    TabRow()
//                }
//
//                // Tab content
////                TabContent()
//            }
//        },
//        bottomBar = {
//            ButtonSection(
//                navigateToHome = navigateToHome
//            )
//        }
//    )
//}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun RecommendedPlanScreen(
    navigateToHome: () -> Unit = {}
) {
    val navController = rememberNavController()
    val pagerState = rememberPagerState(pageCount = 3)
    val tabs = listOf(
        TabItem.Daily,
        TabItem.Weekly,
        TabItem.Monthly
    )

//    BackHandler {
//        if (pagerState.currentPage > 0) {
//            pagerState.scrollToPage(0)
//        }
//    }

    Scaffold(
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Card section
                CardSection()

                // Tab section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TabRow(tabs = tabs, pagerState = pagerState)
                }

                // Tab content
                TabsContent(tabs = tabs, pagerState = pagerState)
            }
        },
        bottomBar = {
            ButtonSection(
                navigateToHome = navigateToHome
            )
        }
    )
}

//@Composable
//fun CardSection() {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        elevation = 4.dp,
//        backgroundColor = MaterialTheme.colors.surface
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            // Section 1
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                // Rounded icon
//                Icon(
//                    Icons.Default.AccountCircle,
//                    contentDescription = stringResource(R.string.initial_income_icon),
//                    modifier = Modifier.size(32.dp)
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                // Title
//                Text(
//                    text = stringResource(R.string.initial_income_title),
//                    style = MaterialTheme.typography.h6
//                )
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Section 2
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(MaterialTheme.colors.surface)
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.padding(horizontal = 16.dp)
//                ) {
//                    // Currency symbol and dropdown arrow
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Text(
//                            text = "Rp.",
//                            style = MaterialTheme.typography.h6
//                        )
//                        Icon(
//                            Icons.Default.ArrowDropDown,
//                            contentDescription = stringResource(R.string.initial_income_icon),
//                            modifier = Modifier.size(32.dp),
//                        )
//                    }
//                    Spacer(modifier = Modifier.width(16.dp))
//                    // Edit text
//                    TextField(
//                        value = "5000", // Placeholder value, replace with your actual value or use state
//                        onValueChange = { /* Handle value change */ },
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                }
//            }
//
//            // Section 3
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//    }
//}

@Composable
fun CardSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Section 1
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Rounded icon
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = stringResource(R.string.initial_income_icon),
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Title
                Text(
                    text = stringResource(R.string.initial_income_title),
                    style = MaterialTheme.typography.h6
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Section 2
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    // Currency symbol and dropdown arrow
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Rp.",
                            style = MaterialTheme.typography.h6
                        )
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = stringResource(R.string.initial_income_icon),
                            modifier = Modifier.size(32.dp),
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    // Edit text
                    TextField(
                        value = "5000", // Placeholder value, replace with your actual value or use state
                        onValueChange = { /* Handle value change */ },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Section 3
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

//@Composable
//fun TabSection() {
//    val tabs = listOf(
//        stringResource(R.string.tab_daily),
//        stringResource(R.string.tab_weekly),
//        stringResource(R.string.tab_monthly)
//    )
//    var selectedTabIndex by remember { mutableStateOf(0) }
//
//    ScrollableTabRow(
//        selectedTabIndex = selectedTabIndex,
//        modifier = Modifier
//            .fillMaxWidth(),
//        edgePadding = 16.dp,
//        backgroundColor = MaterialTheme.colors.surface,
//        indicator = { tabPositions ->
//            TabRowDefaults.Indicator(
//                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
//                color = MaterialTheme.colors.primary
//            )
//        },
//        contentColor = MaterialTheme.colors.primary,
//    ) {
//        tabs.forEachIndexed { index, title ->
//            Tab(
//                selected = selectedTabIndex == index,
//                onClick = { selectedTabIndex = index },
//                modifier = Modifier
//                    .padding(vertical = 8.dp, horizontal = 16.dp)
//            ) {
//                Text(
//                    text = title,
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun TabContent() {
//    when (val selectedTabIndex = 0) {
//        0 -> {
//            DailyContent()
//        }
//
//        1 -> {
//            WeeklyContent()
//        }
//
//        2 -> {
//            MonthlyContent()
//        }
//    }
//}
//
//
//@Composable
//fun DailyContent() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 16.dp),
//        verticalArrangement = Arrangement.spacedBy(4.dp)
//    ) {
//        // Card 1
//        CardItem()
//
//        // Card 2
//        CardItem()
//
//        // Add more cards as needed
//    }
//}

@Composable
fun CardItem() {
    // Mutable state variable to store the quantity value
    var quantity by remember { mutableStateOf(2) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Image
                Image(
                    painter = painterResource(R.drawable.menu1),
                    contentDescription = stringResource(R.string.menu_image),
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    // Title
                    Text(
                        text = "Bakso Pak Udin",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // Price range
                    Text(
                        text = "Rp. 20.000 - Rp. 30.000",
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // Expense type
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Food",
                            style = MaterialTheme.typography.body2
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        // Minus button
                        IconButton(
                            onClick = { if (quantity > 0) quantity-- },
                            modifier = Modifier
                                .size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Minus"
                            )
                        }
                        // Amount
                        Text(
                            text = quantity.toString(),
                            modifier = Modifier.padding(horizontal = 8.dp),
                            style = MaterialTheme.typography.body2
                        )
                        // Plus button
                        IconButton(
                            onClick = { quantity++ },
                            modifier = Modifier
                                .size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Plus"
                            )
                        }
                    }
                }
            }
        }
    }
}


//@Composable
//fun WeeklyContent() {
//    // TODO: Implement the content for the Weekly tab, similar to DailyContent()
//}
//
//@Composable
//fun MonthlyContent() {
//    // TODO: Implement the content for the Monthly tab, similar to DailyContent()
//}

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@ExperimentalPagerApi
//@Composable
//fun TabRow() {
//
//    val tabs = listOf(
//        TabItem.Daily,
//        TabItem.Weekly,
//        TabItem.Monthly,
//    )
//
//    val pagerState = rememberPagerState(pageCount = tabs.size)
//
//    Scaffold() {
//        Column {
//            Tabs(tabs = tabs, pagerState = pagerState)
//            TabsContent(tabs = tabs, pagerState = pagerState)
//        }
//
//    }
//}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabRow(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    androidx.compose.material.TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        contentColor = Color.Black,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                color = Color.Black
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            LeadingIconTab(
                selected = pagerState.currentPage == index,
                icon = { },
                text = { Text(text = tab.title) },
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
//    val scope = rememberCoroutineScope()
//    androidx.compose.material.TabRow(
//        selectedTabIndex = pagerState.currentPage,
//        backgroundColor = Color.Green,
//        contentColor = Color.White,
//        indicator = { tabPositions ->
//            TabRowDefaults.Indicator(
//                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
//                color = Color.White
//            )
//        }
//    ) {
//        tabs.forEachIndexed { index, tab ->
//
//            LeadingIconTab(
//                selected = pagerState.currentPage == index,
//                text = { Text(text = tab.title) },
//                icon = { Icon(painter = painterResource(id = tab.icon), contentDescription = "") },
//                onClick = {
//                    scope.launch {
//                        pagerState.animateScrollToPage(index)
//                    }
//                },
//            )
//        }
//    }
//}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        tabs[page].screen()
    }
}

@Composable
fun DailyTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        CardItem()
        CardItem()
    }
}

@Composable
fun WeeklyTab() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Empty plan",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp
        )
    }
}

@Composable
fun MonthlyTab() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Empty plan",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp
        )
    }
}

@Composable
fun ButtonSection(
    navigateToHome: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ButtonApp(
            text = stringResource(id = R.string.button_text),
            color = MaterialTheme.colors.secondary,
            onClick = { navigateToHome() },
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = { /* Handle map button click */ }
        ) {
            Icon(Icons.Default.LocationOn, contentDescription = stringResource(R.string.map_icon))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendedPlanScreenPreview() {
    BudgetInTheme {
        RecommendedPlanScreen()
    }
}