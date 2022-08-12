package com.vunh.demo_clean_app.domain.login.usecase

import com.vunh.demo_clean_app.data.common.utils.WrappedResponse
import com.vunh.demo_clean_app.data.login.remote.dto.LoginRequest
import com.vunh.demo_clean_app.data.login.remote.dto.LoginResponse
import com.vunh.demo_clean_app.domain.login.LoginRepository
import com.vunh.demo_clean_app.domain.login.entity.LoginEntity
import com.vunh.demo_clean_app.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
//    suspend fun execute(loginRequest: LoginRequest): Flow<BaseResult<LoginEntity, WrappedResponse<LoginResponse>>> {
//        return loginRepository.login(loginRequest)
//    }

    suspend fun invoke(loginRequest: LoginRequest): Flow<BaseResult<LoginEntity, WrappedResponse<LoginResponse>>> {
        return loginRepository.login(loginRequest)
    }

}