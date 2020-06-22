package com.masum.edu_portal.di

import android.app.Application
import com.masum.edu_portal.common.MyApplication
import com.masum.edu_portal.di.viewmodel_module.GlobalViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,ActivityBindingModule::class,AppModule::class,ViewModelFactoryModule::class,GlobalViewModelModule::class])
open interface AppComponent :AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun bindApplicationInstance(application: Application) :Builder

        fun build(): AppComponent
    }
}