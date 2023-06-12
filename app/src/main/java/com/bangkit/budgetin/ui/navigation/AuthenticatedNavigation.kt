package com.bangkit.budgetin.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bangkit.budgetin.R
import com.bangkit.budgetin.ui.itemview.NavigationItem
import com.bangkit.budgetin.ui.screen.home.HomeScreen
import com.bangkit.budgetin.ui.screen.profile.ProfileScreen
import com.bangkit.budgetin.ui.screen.spend.SpendScreen

@Composable
fun AuthenticatedNavigation(
    parentNavController: NavController,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navigationItems = listOf(
        NavigationItem(
            title = "Home",
            icon = painterResource(id = R.drawable.ic_home),
            screen = Screen.Home,
        ),
        NavigationItem(
            title = "Daily Report",
            icon = painterResource(id = R.drawable.ic_spend),
            screen = Screen.Spend,
        ),
        NavigationItem(
            title = "Transaction",
            icon = painterResource(id = R.drawable.ic_transaction),
            screen = Screen.Transaction,
        ),
        NavigationItem(
            title = "Profile",
            icon = painterResource(id = R.drawable.ic_profile),
            screen = Screen.Profile,
        )
    )

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 21.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
                horizontalArrangement =
                if (currentRoute == Screen.Home.route)
                    Arrangement.SpaceBetween
                else
                    Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (currentRoute == Screen.Home.route) {
                    Text(
                        text = "Welcome User123",
                        style = MaterialTheme.typography.h1
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "profile picture",
                        modifier = Modifier
                            .size(30.dp),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        text = navigationItems.find {
                            it.screen.route == currentRoute
                        }?.title ?: "",
                        style = MaterialTheme.typography.h1
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigation(
                modifier = Modifier,
                backgroundColor = Color.White
            ) {
                navigationItems.map { item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = { Text(text = item.title) },
                        selected = currentRoute == item.screen.route,
                        unselectedContentColor = MaterialTheme.colors.primary,
                        selectedContentColor = MaterialTheme.colors.secondary,
                        onClick = {
                            navController.navigate(item.screen.route) {
                                popUpTo(Screen.Home.route) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screen.Home.route,

            ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Spend.route) {
                SpendScreen()
            }
            composable(Screen.Transaction.route) {}
            composable(Screen.Profile.route) {
                ProfileScreen(
                    navigateToSignin = {
                        parentNavController.navigate(Screen.SignIn.route) {
                            popUpTo(Screen.Home.route) {inclusive = true}
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
