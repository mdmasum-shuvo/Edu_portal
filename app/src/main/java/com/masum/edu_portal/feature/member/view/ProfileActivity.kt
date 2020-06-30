package com.masum.edu_portal.feature.member.view

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseActivity
import com.masum.edu_portal.common.Constant
import com.masum.edu_portal.databinding.ActivityProfileBinding
import com.masum.edu_portal.feature.home.data.class_mate.Datum

class ProfileActivity : BaseActivity() {

   private lateinit var binding :ActivityProfileBinding

    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_profile
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initComponent() {
        binding= getBinding() as ActivityProfileBinding
        setNotificationBarTransparent()
        setNotificationBarBlackNWhite()
        setEnterAnimation()
    }

    override fun initFunctionality() {
        var bundle=intent.extras
        var studentData=bundle!!.getSerializable(Constant.INTENT_KEY) as Datum
        binding.data= studentData
    }

    override fun initListener() {
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {

    }


    fun upArrowClicked(view: View) {
        onBackPressed()
    }

}
