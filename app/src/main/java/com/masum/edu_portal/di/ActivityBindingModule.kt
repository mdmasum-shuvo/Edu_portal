package com.masum.edu_portal.di

import com.masum.edu_portal.di.viewmodel_module.*
import com.masum.edu_portal.feature.exam.view.ExamActivity
import com.masum.edu_portal.feature.exam.view.QuestionActivity
import com.masum.edu_portal.feature.home.view.HomeActivity
import com.masum.edu_portal.feature.homework.view.HomeWorkActivity
import com.masum.edu_portal.feature.launch.view.LauncherActivity
import com.masum.edu_portal.feature.member.view.MyProfileActivity
import com.masum.edu_portal.feature.member.view.ProfileActivity
import com.masum.edu_portal.feature.myclass.view.MyClassActivity
import com.masum.edu_portal.feature.others.OthersActivity
import com.masum.edu_portal.feature.study.view.StudyActivity
import com.masum.edu_portal.feature.study.view.ViewViewActivity
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

    @ContributesAndroidInjector(modules = [ProfileViewModelModule::class])
    abstract fun myProfileActivity(): MyProfileActivity

    @ContributesAndroidInjector(modules = [GlobalViewModelModule::class,ExamViewModelModule::class])
    abstract fun examActivity(): ExamActivity

    @ContributesAndroidInjector(modules = [StudyViewModelModule::class])
    abstract fun studyActivity(): StudyActivity

    @ContributesAndroidInjector(modules = [HomeworkViewModelModule::class])
    abstract fun homeworkActivity():HomeWorkActivity

    @ContributesAndroidInjector(modules = [ExamViewModelModule::class])
    abstract fun questionActivity():QuestionActivity


    @ContributesAndroidInjector(modules = [AcademyViewmodelModule::class])
    abstract fun otherActivity():OthersActivity

    @ContributesAndroidInjector(modules = [StudyViewModelModule::class])
    abstract fun videoViewActivity(): ViewViewActivity

}