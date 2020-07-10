package com.masum.edu_portal.feature.exam.view

import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseActivity
import com.masum.edu_portal.databinding.ActivityQuestionBinding

class QuestionActivity : BaseActivity() {
    lateinit var binding:ActivityQuestionBinding
    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_question
    }

    override fun initComponent() {
        binding=getBinding() as ActivityQuestionBinding
    }

    override fun initFunctionality() {
    }

    override fun initListener() {
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
    }

}