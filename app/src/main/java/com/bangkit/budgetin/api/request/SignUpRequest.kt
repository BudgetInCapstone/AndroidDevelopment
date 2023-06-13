package com.bangkit.budgetin.api.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpRequest(
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
): Parcelable
