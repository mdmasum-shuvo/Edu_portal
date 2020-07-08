package com.masum.edu_portal.feature.exam.view

import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseActivity
import com.masum.edu_portal.databinding.ActivityExamBinding
import com.masum.edu_portal.di.ViewModelProviderFactory
import javax.inject.Inject

class ExamActivity : BaseActivity() {
    private lateinit var binding: ActivityExamBinding
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun getLayoutResourceFile(): Int {
       return R.layout.activity_exam
    }

    override fun initComponent() {
       binding=getBinding() as ActivityExamBinding
    }

    override fun initFunctionality() {
    }

    override fun initListener() {
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
    }

}