/*
 * *
 *  * Created by Md Masum Talukder on 5/6/20 3:17 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/6/20 3:17 AM
 *
 */

package com.masum.edu_portal.feature.launch.view

import android.os.Bundle
import com.masum.edu_portal.R
import com.masum.edu_portal.di.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LauncherActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

    }

}
