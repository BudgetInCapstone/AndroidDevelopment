package com.bangkit.budgetin.api.item

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpItem(
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
): Parcelable
