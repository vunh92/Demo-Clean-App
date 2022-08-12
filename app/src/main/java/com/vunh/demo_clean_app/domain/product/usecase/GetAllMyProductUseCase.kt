package com.vunh.demo_clean_app.domain.product.usecase

import com.vunh.demo_clean_app.data.common.utils.WrappedListResponse
import com.vunh.demo_clean_app.data.product.remote.dto.ProductResponse
import com.vunh.demo_clean_app.domain.common.BaseResult
import com.vunh.demo_clean_app.domain.product.ProductRepository
import com.vunh.demo_clean_app.domain.product.entity.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMyProductUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun invoke() : Flow<BaseResult<List<ProductEntity>, WrappedListResponse<ProductResponse>>> {
        return productRepository.getAllMyProducts()
    }
}
