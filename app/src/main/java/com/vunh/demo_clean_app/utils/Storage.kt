package com.vunh.demo_clean_app.utils

interface Storage {
    fun setString(key: String, value: String)
    fun getString(key: String): String
}