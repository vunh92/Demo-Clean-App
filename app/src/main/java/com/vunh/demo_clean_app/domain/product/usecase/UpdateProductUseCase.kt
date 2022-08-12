package com.vunh.demo_clean_app.domain.product.usecase

import com.vunh.demo_clean_app.data.common.utils.WrappedResponse
import com.vunh.demo_clean_app.data.product.remote.dto.ProductResponse
import com.vunh.demo_clean_app.data.product.remote.dto.ProductUpdateRequest
import com.vunh.demo_clean_app.domain.common.BaseResult
import com.vunh.demo_clean_app.domain.product.ProductRepository
import com.vunh.demo_clean_app.domain.product.entity.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(private val productRepository: ProductRepository){
    suspend fun invoke(productUpdateRequest: ProductUpdateRequest, id: String) : Flow<BaseResult<ProductEntity, WrappedResponse<ProductResponse>>> {
        return productRepository.updateProduct(productUpdateRequest, id)
    }
}
