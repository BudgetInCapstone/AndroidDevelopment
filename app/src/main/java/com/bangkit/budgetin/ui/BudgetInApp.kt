package com.bangkit.budgetin.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bangkit.budgetin.ui.navigation.Screen
import com.bangkit.budgetin.ui.screen.signin.SignInScreen

@Composable
fun BudgetInApp(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold() { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.SignIn.route,
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colors.background)
        ){
            composable(Screen.SignIn.route){
                SignInScreen()
            }
            composable(Screen.SignUp.route){}
            composable(Screen.Home.route){}
            composable(Screen.AddPlan.route){}
        }
    }
}