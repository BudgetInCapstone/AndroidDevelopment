package com.bangkit.budgetin.api

import com.bangkit.budgetin.api.request.RecommendationRequest
import com.bangkit.budgetin.api.request.SignInRequest
import com.bangkit.budgetin.api.request.SignUpRequest
import com.bangkit.budgetin.api.response.RecommendationResponse
import com.bangkit.budgetin.api.response.AuthResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): Response<AuthResponse>

    @Headers("Content-Type: application/json")
    @POST("register")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): Response<AuthResponse>
}