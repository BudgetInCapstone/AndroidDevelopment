package com.bangkit.budgetin.api.item

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignInItem(
    val email: String = "",
    val password: String = "",
) : Parcelable
