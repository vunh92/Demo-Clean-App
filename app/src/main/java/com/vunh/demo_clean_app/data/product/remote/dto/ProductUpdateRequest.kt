package com.vunh.demo_clean_app.data.product.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductUpdateRequest(
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Int
)
