package com.vunh.demo_clean_app.domain.login

import com.vunh.demo_clean_app.data.common.utils.WrappedResponse
import com.vunh.demo_clean_app.data.login.remote.dto.LoginRequest
import com.vunh.demo_clean_app.data.login.remote.dto.LoginResponse
import com.vunh.demo_clean_app.domain.login.entity.LoginEntity
import com.vunh.demo_clean_app.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest) : Flow<BaseResult<LoginEntity, WrappedResponse<LoginResponse>>>
}