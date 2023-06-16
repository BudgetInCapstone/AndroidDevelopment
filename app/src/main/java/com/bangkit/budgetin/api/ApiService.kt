package com.bangkit.budgetin.api

import com.bangkit.budgetin.api.request.SignInRequest
import com.bangkit.budgetin.api.request.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("signin")
    fun signIn(
        @Body signInRequest: SignInRequest
    ): String // TODO: diganti bentuk response API

    @Headers("Content-Type: application/json")
    @POST("signup")
    fun signUp(
        @Body signUpRequest: SignUpRequest
    ): String // TODO: diganti bentuk response API


}