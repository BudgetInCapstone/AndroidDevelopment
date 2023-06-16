package com.bangkit.budgetin.api.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignInRequest(
    val email: String = "",
    val password: String = "",
) : Parcelable
