package com.bangkit.budgetin.di

import android.content.Context
import com.bangkit.budgetin.api.ApiConfig
import com.bangkit.budgetin.api.RecApiConfig
import com.bangkit.budgetin.data.AuthStore
import com.bangkit.budgetin.repo.AuthenticationRepository
import com.bangkit.budgetin.repo.RecommendationRepository

object Injection {
    fun provideRecommendationRepository(): RecommendationRepository{
        val recApiService = RecApiConfig.getApiService()
        return RecommendationRepository(recApiService)
    }

    fun provideAuthRepository(context: Context): AuthenticationRepository{
        val apiService = ApiConfig.getApiService()
        val store = AuthStore(context)
        return AuthenticationRepository(apiService, store)
    }
}