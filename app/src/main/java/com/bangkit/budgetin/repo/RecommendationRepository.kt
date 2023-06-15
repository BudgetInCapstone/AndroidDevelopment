package com.bangkit.budgetin.repo

import android.util.Log
import com.bangkit.budgetin.api.ApiService
import com.bangkit.budgetin.api.RecApiService
import com.bangkit.budgetin.api.request.RecommendationRequest
import com.bangkit.budgetin.api.response.RecommendationResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RecommendationRepository(private val apiService: RecApiService) {
    fun generateRecommendation(
        recommendationRequest: RecommendationRequest,
    ): Flow<RecommendationResponse> {
        Log.d("RecommendationDebug", "oke1")
        Log.d("RecommendationDebug", recommendationRequest.toString())
        val recommendationResponse = apiService.generateRecommendation(recommendationRequest)
        Log.d("RecommendationDebug", "oke2")
        return flowOf(recommendationResponse)
    }
}