/*
 * *
 *  * Created by Md Masum Talukder on 5/6/20 3:17 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/6/20 3:17 AM
 *
 */

package com.masum.edu_portal.feature.launch.view

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.masum.edu_portal.R

class LauncherActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_launcher)
    }

}
