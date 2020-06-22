package com.masum.edu_portal.di

import com.masum.edu_portal.feature.home.view.HomeActivity
import com.masum.edu_portal.feature.launch.view.LauncherActivity
import com.masum.edu_portal.feature.member.view.ProfileActivity
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

}