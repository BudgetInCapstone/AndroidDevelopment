package com.bangkit.budgetin.ui.navigation

sealed class Screen (val route: String){
    object Home: Screen("home")
    object SignIn: Screen("signin")
    object SignUp: Screen("signup")
    object AddPlan: Screen("addplan")
}