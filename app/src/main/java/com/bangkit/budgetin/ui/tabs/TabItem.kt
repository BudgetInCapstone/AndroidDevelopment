package com.bangkit.budgetin.ui.tabs

import androidx.compose.runtime.Composable
import com.bangkit.budgetin.ui.screen.home.HomeScreen
import com.bangkit.budgetin.ui.screen.profile.ProfileScreen
import com.bangkit.budgetin.ui.screen.recommend.DailyTab
import com.bangkit.budgetin.ui.screen.recommend.MonthlyTab
import com.bangkit.budgetin.ui.screen.recommend.WeeklyTab


sealed class TabItem(var title: String, var screen: @Composable () -> Unit) {
    object Daily : TabItem("Daily", { DailyTab() })
    object Weekly : TabItem("Weekly", { WeeklyTab() })
    object Monthly : TabItem("Monthly", { MonthlyTab() })
}