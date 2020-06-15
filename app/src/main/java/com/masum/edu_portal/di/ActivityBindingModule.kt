package com.masum.edu_portal.di

import com.masum.edu_portal.di.auth.AuthFragmentBindingModule
import com.masum.edu_portal.di.auth.AuthModule
import com.masum.edu_portal.di.auth.AuthViewModelModule
import com.masum.edu_portal.di.home.HomeFragmentModule
import com.masum.edu_portal.di.scope.AuthScope
import com.masum.edu_portal.feature.home.view.HomeActivity
import com.masum.edu_portal.feature.launch.view.LauncherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
open abstract class ActivityBindingModule {

    @AuthScope
    @ContributesAndroidInjector(modules = [AuthFragmentBindingModule::class,AuthModule::class,AuthViewModelModule::class])
    abstract fun launcherActivityBinding(): LauncherActivity

    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun homeActivity(): HomeActivity

}