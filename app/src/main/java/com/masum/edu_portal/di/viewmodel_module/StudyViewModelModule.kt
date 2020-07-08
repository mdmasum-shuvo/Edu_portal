package com.masum.edu_portal.di.viewmodel_module

import androidx.lifecycle.ViewModel
import com.masum.edu_portal.di.scope.ViewModelKey
import com.masum.edu_portal.myviewmodel.StudyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StudyViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(StudyViewModel::class)
    abstract fun bindStudyViewModel(studyViewModel: StudyViewModel): ViewModel
}