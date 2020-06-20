package com.masum.edu_portal.di.user

import androidx.lifecycle.ViewModel
import com.masum.edu_portal.di.scope.ViewModelKey
import com.masum.edu_portal.myviewmodel.StudentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StudentViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StudentViewModel::class)
    abstract fun bindStudentViewModel(viewModel: StudentViewModel):ViewModel
}