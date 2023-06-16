package com.bangkit.budgetin.ui.itemview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NeedsRepeat(
    val needs: String,
    val number: Int,
): Parcelable
