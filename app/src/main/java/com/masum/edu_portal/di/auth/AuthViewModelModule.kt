package com.masum.edu_portal.di.auth

import androidx.lifecycle.ViewModel
import com.masum.edu_portal.di.scope.AuthScope
import com.masum.edu_portal.di.scope.ViewModelKey
import com.masum.edu_portal.myviewmodel.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
open abstract class AuthViewModelModule {

    @AuthScope
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    open abstract fun bindViewModelKey(viewModel: AuthViewModel): ViewModel
}