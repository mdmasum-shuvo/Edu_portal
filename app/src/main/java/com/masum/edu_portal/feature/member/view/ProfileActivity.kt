package com.masum.edu_portal.feature.member.view

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseActivity
import com.masum.edu_portal.databinding.ActivityProfileBinding

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
    }

    override fun initListener() {
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {

    }


    fun upArrowClicked(view: View) {
        onBackPressed()
    }

}
