package com.masum.edu_portal.di.viewmodel_module

import androidx.lifecycle.ViewModel
import com.masum.edu_portal.di.scope.ViewModelKey
import com.masum.edu_portal.myviewmodel.HomeWorkViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeworkViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeWorkViewModel::class)
    abstract fun bindHomeworkModule(homeWorkViewModel: HomeWorkViewModel): ViewModel
}