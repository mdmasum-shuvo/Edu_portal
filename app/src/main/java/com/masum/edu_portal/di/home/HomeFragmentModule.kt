package com.masum.edu_portal.di.home

import com.masum.edu_portal.feature.home.view.HomeDashboardFragment
import com.masum.edu_portal.feature.home.view.PostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentModule {
    @ContributesAndroidInjector
    abstract fun homeDashboard():HomeDashboardFragment

    @ContributesAndroidInjector
    abstract fun postFragment():PostFragment

}