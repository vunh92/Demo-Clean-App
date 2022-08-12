package com.vunh.demo_clean_app.domain.register.usecase

import com.vunh.demo_clean_app.data.common.utils.WrappedResponse
import com.vunh.demo_clean_app.data.register.remote.dto.RegisterRequest
import com.vunh.demo_clean_app.data.register.remote.dto.RegisterResponse
import com.vunh.demo_clean_app.domain.common.BaseResult
import com.vunh.demo_clean_app.domain.register.RegisterRepository
import com.vunh.demo_clean_app.domain.register.entity.RegisterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val registerRepository: RegisterRepository) {
    suspend fun invoke(registerRequest: RegisterRequest) : Flow<BaseResult<RegisterEntity, WrappedResponse<RegisterResponse>>> {
        return registerRepository.register(registerRequest)
    }
}