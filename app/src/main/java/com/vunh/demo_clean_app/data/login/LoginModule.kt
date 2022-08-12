package com.vunh.demo_clean_app.data.login

import com.vunh.demo_clean_app.data.common.module.NetworkModule
import com.vunh.demo_clean_app.data.login.remote.api.LoginApi
import com.vunh.demo_clean_app.data.login.repository.LoginRepositoryImpl
import com.vunh.demo_clean_app.domain.login.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class LoginModule {
    @Singleton
    @Provides
    fun provideLoginApi(retrofit: Retrofit) : LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(loginApi: LoginApi) : LoginRepository {
        return LoginRepositoryImpl(loginApi)
    }
}