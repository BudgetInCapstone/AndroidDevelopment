package com.bangkit.budgetin.ui.itemview

data class PlanItem(
    val type: String = "Food",
    val repeat: NeedsRepeat = Repeat.Daily
)
