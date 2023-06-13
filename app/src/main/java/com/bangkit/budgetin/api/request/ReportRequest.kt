package com.bangkit.budgetin.api.request

data class ReportRequest(
    val productType: String? = null,
    val productName: String? = null,
    val currencyType: String? = null,
    val price: String? = null
)
