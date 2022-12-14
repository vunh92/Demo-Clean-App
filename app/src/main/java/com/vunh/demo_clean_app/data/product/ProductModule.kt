package com.vunh.demo_clean_app.data.product

import com.vunh.demo_clean_app.data.common.module.NetworkModule
import com.vunh.demo_clean_app.data.product.remote.api.ProductApi
import com.vunh.demo_clean_app.data.product.repository.ProductRepositoryImpl
import com.vunh.demo_clean_app.domain.product.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ProductModule {
    @Singleton
    @Provides
    fun provideProductApi(retrofit: Retrofit) : ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Singleton
    @Provides
    fun provideProductRepository(productApi: ProductApi) : ProductRepository {
        return ProductRepositoryImpl(productApi)
    }
}