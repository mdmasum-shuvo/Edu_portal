package com.masum.edu_portal.di.viewmodel_module

import androidx.lifecycle.ViewModel
import com.masum.edu_portal.di.scope.ViewModelKey
import com.masum.edu_portal.myviewmodel.ExamViewModel
import com.masum.edu_portal.myviewmodel.GlobalViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ExamViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ExamViewModel::class)
    abstract fun bindExamViewModel(examViewModel: ExamViewModel): ViewModel
}