package com.masum.edu_portal.di.viewmodel_module

import androidx.lifecycle.ViewModel
import com.masum.edu_portal.di.scope.ViewModelKey
import com.masum.edu_portal.myviewmodel.GlobalViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GlobalViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GlobalViewModel::class)
    abstract fun bindGlobalViewModel(globalViewModel: GlobalViewModel):ViewModel
}