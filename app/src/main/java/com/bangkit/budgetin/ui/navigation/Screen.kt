package com.bangkit.budgetin.ui.navigation

sealed class Screen (val route: String){
    object Splash: Screen("splash")
    object Home: Screen("home")
    object SignIn: Screen("signin")
    object SignUp: Screen("signup")
    object Spend: Screen("spend")
    object Transaction: Screen("transaction")
    object Profile: Screen("profile")
    object AddPlan: Screen("addplan")
    object Recommendation: Screen("recommendation")
}