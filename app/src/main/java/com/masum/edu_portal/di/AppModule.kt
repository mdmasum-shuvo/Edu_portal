package com.masum.edu_portal.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.masum.edu_portal.BuildConfig
import com.masum.edu_portal.common.Constant
import com.masum.edu_portal.networks.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class AppModule {

    @Singleton
    @Provides
    open fun provideSharePrefInstance(application: Application): SharedPreferences {
        return application.getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    open fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}