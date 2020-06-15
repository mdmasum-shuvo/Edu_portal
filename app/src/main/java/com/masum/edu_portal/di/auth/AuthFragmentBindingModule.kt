package com.masum.edu_portal.di.auth

import com.masum.edu_portal.feature.auth.view.LoginFragment
import com.masum.edu_portal.feature.auth.view.RegistrationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthFragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun loginFragment():LoginFragment
    @ContributesAndroidInjector
    abstract fun registrationFragment():RegistrationFragment


}