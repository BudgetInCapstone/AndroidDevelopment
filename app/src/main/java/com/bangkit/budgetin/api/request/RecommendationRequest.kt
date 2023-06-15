package com.bangkit.budgetin.api.request

data class RecommendationRequest(
    val max: Long,
    val min: Long,
    val lat: Double,
    val long: Double,
    val radius: Int,
)
