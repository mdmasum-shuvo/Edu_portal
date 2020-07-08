package com.masum.edu_portal.di

import com.masum.edu_portal.di.user.StudentViewModelModule
import com.masum.edu_portal.di.viewmodel_module.ClassViewModelModule
import com.masum.edu_portal.di.viewmodel_module.ExamViewModelModule
import com.masum.edu_portal.feature.home.view.HomeDashboardFragment
import com.masum.edu_portal.feature.home.view.NotificationFragment
import com.masum.edu_portal.feature.home.view.PostFragment
import com.masum.edu_portal.feature.member.view.GalleryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBindingModule {

    @ContributesAndroidInjector(modules = [StudentViewModelModule::class,ExamViewModelModule::class,ClassViewModelModule::class])
    abstract fun homeDashBoardFragment():HomeDashboardFragment


    @ContributesAndroidInjector()
    abstract fun newsFeedFragment():PostFragment

    @ContributesAndroidInjector()
    abstract fun galleryFragment():GalleryFragment

    @ContributesAndroidInjector()
    abstract fun notificationFragment():NotificationFragment

}