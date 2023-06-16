package com.bangkit.budgetin.ui.itemview

object Repeat {
    val Daily = NeedsRepeat(
        needs = "Daily",
        number = 1,
    )
    val Weekly = NeedsRepeat(
        needs = "Weekly",
        number = 1,
    )
    val Monthly = NeedsRepeat(
        needs = "Monthly",
        number = 1,
    )

    fun getNeedsRepeat(needs: String): NeedsRepeat {
        val needsRepeatList = listOf(Daily, Weekly, Monthly)
        return needsRepeatList.first{
            it.needs == needs
        }
    }
}