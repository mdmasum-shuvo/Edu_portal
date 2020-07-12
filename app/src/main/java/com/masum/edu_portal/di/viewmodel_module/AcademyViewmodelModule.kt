package com.masum.edu_portal.di.viewmodel_module

import androidx.lifecycle.ViewModel
import com.masum.edu_portal.di.scope.ViewModelKey
import com.masum.edu_portal.myviewmodel.AcademyViewModel
import com.masum.edu_portal.myviewmodel.StudyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class AcademyViewmodelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AcademyViewModel::class)
    abstract fun bindAcademyViewmodel(academyViewModel: AcademyViewModel): ViewModel
}