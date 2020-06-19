package com.masum.edu_portal.di

import com.masum.edu_portal.di.auth.AuthViewModelModule
import com.masum.edu_portal.di.scope.AuthScope
import com.masum.edu_portal.feature.auth.view.LoginFragment
import com.masum.edu_portal.feature.launch.view.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
open abstract class AuthFragmentBindingModule {

    @AuthScope
    @ContributesAndroidInjector(modules = [AuthViewModelModule::class])
    open abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector()
    open abstract fun splashFragment(): SplashFragment

}