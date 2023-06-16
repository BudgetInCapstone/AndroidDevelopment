package com.bangkit.budgetin.api.request

import android.os.Parcelable
import com.bangkit.budgetin.ui.itemview.NeedsRepeat
import com.bangkit.budgetin.ui.itemview.Repeat
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecommendationRequest(
    val max: Long? = null,
    val min: Long? = null,
    val lat: Double? = null,
    val long: Double? = null,
    val radius: Int? = null,
): Parcelable
