/*
 * *
 *  * Created by Md Masum Talukder on 5/9/20 4:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/9/20 4:56 AM
 *
 */

package com.masum.edu_portal.feature.home.view

import android.os.Build
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseActivity
import com.masum.edu_portal.databinding.ActivityUploadPostBinding

class UploadPostActivity : BaseActivity() {
    lateinit var binding: ActivityUploadPostBinding
    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_upload_post
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initComponent() {
        binding = getBinding() as ActivityUploadPostBinding
        initToolbar()
        enableBackButton()
       // setOnlyStatusBarTransparent()
        binding.tvSavePost.alpha = 0.5F
        setEnterAnimation()
    }

    override fun initFunctionality() {
    }

    override fun initListener() {
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
    }

    override fun onRefresh() {
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
//            onBackPressed();
//            Snackbar.make(floatingbar,"Clicked",Snackbar.LENGTH_SHORT).show();
            //startActivity(new Intent(HomeActivity.this, HistoryActivity.class));
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
