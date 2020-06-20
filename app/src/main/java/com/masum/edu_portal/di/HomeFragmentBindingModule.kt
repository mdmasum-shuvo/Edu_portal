package com.masum.edu_portal.di

import com.masum.edu_portal.di.user.StudentViewModelModule
import com.masum.edu_portal.feature.home.view.HomeDashboardFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBindingModule {


    @ContributesAndroidInjector(modules = [StudentViewModelModule::class])
    abstract fun homeDashBoardFragment():HomeDashboardFragment

}