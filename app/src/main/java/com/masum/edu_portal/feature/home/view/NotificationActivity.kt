/*
 * *
 *  * Created by Md Masum Talukder on 5/15/20 4:15 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/15/20 4:15 AM
 *
 */

package com.masum.edu_portal.feature.home.view

import android.app.Activity
import android.os.Build
import androidx.recyclerview.widget.LinearLayoutManager
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseActivity
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.fragment_post.pullToRefresh

class NotificationActivity : BaseActivity() {
    private lateinit var mActivity: Activity

    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_notification
    }

    override fun initComponent() {
        mActivity = this

        setNotificationBarTransparent()
        setNotificationBarBlackNWhite()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setEnterAnimation()
        }
        rv.setHasFixedSize(true)
        val layoutManagerV = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
        rv.layoutManager = layoutManagerV
    }

    override fun initFunctionality() {
    }

    override fun initListener() {


    }

    override fun onRefresh() {
        pullToRefresh.isRefreshing = true
    }

    private fun callData() {
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
    }

}
