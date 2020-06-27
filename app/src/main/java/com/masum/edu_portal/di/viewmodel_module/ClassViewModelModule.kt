package com.masum.edu_portal.di.viewmodel_module

import androidx.lifecycle.ViewModel
import com.masum.edu_portal.di.scope.ViewModelKey
import com.masum.edu_portal.myviewmodel.ClassViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ClassViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ClassViewModel::class)
    abstract fun bindClassViewModel(classViewModel: ClassViewModel): ViewModel
}