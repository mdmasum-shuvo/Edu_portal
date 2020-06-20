package com.masum.edu_portal.di

import com.masum.edu_portal.di.scope.AuthScope
import com.masum.edu_portal.feature.home.view.HomeActivity
import com.masum.edu_portal.feature.launch.view.LauncherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
open abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [AuthFragmentBindingModule::class])
    open abstract fun launcherActivityBinding(): LauncherActivity

    @ContributesAndroidInjector(modules = [HomeFragmentBindingModule::class])
    open abstract fun homeActivity(): HomeActivity

}