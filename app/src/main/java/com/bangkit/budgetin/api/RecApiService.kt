package com.bangkit.budgetin.api

import com.bangkit.budgetin.api.request.RecommendationRequest
import com.bangkit.budgetin.api.response.RecommendationResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RecApiService {
    @Headers("Content-Type: application/json")
    @POST("get_resto")
    fun generateRecommendation(
        @Body recommendationRequest: RecommendationRequest
    ): RecommendationResponse
}