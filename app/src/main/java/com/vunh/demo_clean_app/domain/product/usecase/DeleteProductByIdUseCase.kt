package com.vunh.demo_clean_app.domain.product.usecase

import com.vunh.demo_clean_app.data.common.utils.WrappedResponse
import com.vunh.demo_clean_app.data.product.remote.dto.ProductResponse
import com.vunh.demo_clean_app.domain.common.BaseResult
import com.vunh.demo_clean_app.domain.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteProductByIdUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun invoke(id: String) : Flow<BaseResult<Unit, WrappedResponse<ProductResponse>>> {
        return productRepository.deleteProductById(id)
    }
}
