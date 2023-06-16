package com.bangkit.budgetin.ui.navigation

sealed class Screen (val route: String){
    object Home: Screen("home/{success}"){
        fun createRoute(success: Boolean) = "home/$success"
    }
    object SignIn: Screen("signin")
    object SignUp: Screen("signup")
    object Spend: Screen("spend")
    object Transaction: Screen("transaction")
    object Profile: Screen("profile")
    object AddPlan: Screen("addplan")
    object Recommendation: Screen("recommendation")
}