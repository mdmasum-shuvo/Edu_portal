package com.masum.edu_portal.di.auth

import com.masum.edu_portal.di.scope.AuthScope
import com.masum.edu_portal.networks.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    @AuthScope
    @Provides
    fun provideAuthApi(retrofit: Retrofit):AuthApi{
        return retrofit.create(AuthApi::class.java)
    }
}