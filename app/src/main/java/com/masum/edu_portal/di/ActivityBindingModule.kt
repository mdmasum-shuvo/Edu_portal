package com.masum.edu_portal.di

import com.masum.edu_portal.di.viewmodel_module.ClassViewModelModule
import com.masum.edu_portal.feature.home.view.HomeActivity
import com.masum.edu_portal.feature.launch.view.LauncherActivity
import com.masum.edu_portal.feature.member.view.ProfileActivity
import com.masum.edu_portal.feature.myclass.view.MyClassActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [AuthFragmentBindingModule::class])
    abstract fun launcherActivityBinding(): LauncherActivity

    @ContributesAndroidInjector(modules = [HomeFragmentBindingModule::class])
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector()
    abstract fun profileActivity(): ProfileActivity

    @ContributesAndroidInjector(modules = [ClassViewModelModule::class])
    abstract fun myClassActivity(): MyClassActivity

}