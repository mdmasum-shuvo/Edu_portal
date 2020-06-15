package com.masum.edu_portal.di

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class MySharedPreferences @Inject constructor(private val mSharedPreferences: SharedPreferences) {

    fun putData(key: String?, data: String) {
        mSharedPreferences.edit().putString(key, data).apply()
    }

    fun getString(key: String?): String {
        return mSharedPreferences.getString(key, "").toString()
    }

    fun putData(key: String?, data: Boolean) {
        mSharedPreferences.edit().putBoolean(key, data).apply()
    }

    fun getBoolean(key: String?): Boolean {
        return mSharedPreferences.getBoolean(key, false)
    }


    fun clear() {
        mSharedPreferences.edit().clear()
    }

}