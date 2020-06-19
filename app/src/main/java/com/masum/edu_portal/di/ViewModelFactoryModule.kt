package com.masum.edu_portal.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
open abstract class ViewModelFactoryModule {
    @Binds
   open abstract fun bindViewModelProviderFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}