package com.bangkit.budgetin.api.response

import com.google.gson.annotations.SerializedName

data class RecommendationResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("status")
	val status: String
)

data class DataItem(

	@field:SerializedName("merchant_area")
	val merchantArea: String,

	@field:SerializedName("max_price")
	val maxPrice: Int,

	@field:SerializedName("min_price")
	val minPrice: Int,

	@field:SerializedName("distance")
	val distance: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("location")
	val location: Location
)

data class Location(

	@field:SerializedName("latitude")
	val latitude: Double,

	@field:SerializedName("longitude")
	val longitude: Double
)
