package com.bangkit.budgetin.ui

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bangkit.budgetin.ui.navigation.AuthenticatedNavigation
import com.bangkit.budgetin.ui.navigation.Screen
import com.bangkit.budgetin.ui.screen.signin.SignInScreen
import com.bangkit.budgetin.ui.screen.signup.SignUpScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignIn.route,
        modifier = Modifier
            .background(MaterialTheme.colors.background)
    ) {
        composable(Screen.SignIn.route) {
            SignInScreen(
                navigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                },
                navigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.SignIn.route){inclusive = true}
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(
                navigateToSignIn = {
                    navController.navigate(Screen.SignIn.route) {
                        popUpTo(Screen.SignIn.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            AuthenticatedNavigation(
                parentNavController = navController
            )
        }
    }
}
