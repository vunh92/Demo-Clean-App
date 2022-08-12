package com.vunh.demo_clean_app.domain.product

import com.vunh.demo_clean_app.data.common.utils.WrappedListResponse
import com.vunh.demo_clean_app.data.common.utils.WrappedResponse
import com.vunh.demo_clean_app.data.product.remote.dto.ProductCreateRequest
import com.vunh.demo_clean_app.data.product.remote.dto.ProductResponse
import com.vunh.demo_clean_app.data.product.remote.dto.ProductUpdateRequest
import com.vunh.demo_clean_app.domain.common.BaseResult
import com.vunh.demo_clean_app.domain.product.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getAllMyProducts() : Flow<BaseResult<List<ProductEntity>, WrappedListResponse<ProductResponse>>>
    suspend fun getProductById(id: String) : Flow<BaseResult<ProductEntity, WrappedResponse<ProductResponse>>>
    suspend fun updateProduct(productUpdateRequest: ProductUpdateRequest, id: String) : Flow<BaseResult<ProductEntity, WrappedResponse<ProductResponse>>>
    suspend fun deleteProductById(id: String) : Flow<BaseResult<Unit, WrappedResponse<ProductResponse>>>
    suspend fun createProduct(productCreateRequest: ProductCreateRequest) : Flow<BaseResult<ProductEntity, WrappedResponse<ProductResponse>>>
}