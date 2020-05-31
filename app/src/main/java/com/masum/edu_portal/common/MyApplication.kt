package com.masum.edu_portal.common

import android.app.Application
import android.content.Context

class MyApplication :Application() {


    private var application: Application? = null

   override fun onCreate() {
        super.onCreate()
        application = this
    }

    fun getContext(): Context? {
        return application
    }
}