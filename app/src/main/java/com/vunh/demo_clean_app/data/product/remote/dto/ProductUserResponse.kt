package com.vunh.demo_clean_app.data.product.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductUserResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("email") var email: String
)
