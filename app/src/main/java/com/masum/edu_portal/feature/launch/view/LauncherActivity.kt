/*
 * *
 *  * Created by Md Masum Talukder on 5/6/20 3:17 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/6/20 3:17 AM
 *
 */

package com.masum.edu_portal.feature.launch.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.masum.edu_portal.R
import com.masum.edu_portal.di.ViewModelProviderFactory
import com.masum.edu_portal.myviewmodel.AuthViewModel
import com.masum.edu_portal.myviewmodel.GlobalViewModel
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker
import com.treebo.internetavailabilitychecker.InternetConnectivityListener
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LauncherActivity : DaggerAppCompatActivity(), InternetConnectivityListener {

    private lateinit var viewmodel: GlobalViewModel
    var mInternetAvailabilityChecker: InternetAvailabilityChecker? = null

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        viewmodel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(GlobalViewModel::class.java)
        InternetAvailabilityChecker.init(this)

        mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance()
        mInternetAvailabilityChecker!!.addInternetConnectivityListener(this)
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
        if (isConnected) {
            viewmodel.getOrganizationList()
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }


}
