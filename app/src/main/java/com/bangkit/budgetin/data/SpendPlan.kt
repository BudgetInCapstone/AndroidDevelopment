package com.bangkit.budgetin.data

data class SpendPlan(
    val category: PlanCategory,
    var type: PlanType,
    var expanded: Boolean = false
)