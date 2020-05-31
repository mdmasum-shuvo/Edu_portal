/*
 * *
 *  * Created by Md Masum Talukder on 5/6/20 3:25 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/6/20 3:25 AM
 *
 */

package com.masum.edu_portal.feature.launch.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.masum.edu_portal.BuildConfig
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseFragment
import com.masum.edu_portal.databinding.FragmentSplashBinding
import com.masum.edu_portal.feature.home.view.HomeActivity
import com.masum.edu_portal.utils.SharedPreferencesEnum
import org.jsoup.Jsoup
import java.util.concurrent.ExecutionException

class SplashFragment : BaseFragment() {
    lateinit var navController: NavController
    lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate<FragmentSplashBinding>(
                inflater,
                R.layout.fragment_splash!!, container, false
            )

        mActivity = activity as Activity
        return binding!!.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        nextPage()
    }

    override fun onResume() {
        super.onResume()
        //checkVersionUpdate()
    }

    @SuppressLint("StaticFieldLeak")
    class VersionChecker constructor(private val mActivity: Activity) :
        AsyncTask<String?, String?, String?>() {
        private val newVersion: String? = null
        override fun doInBackground(vararg p0: String?): String? {
            var newVersion: String? = null
            return try {
                newVersion =
                    Jsoup.connect("https://play.google.com/store/apps/details?id=" + mActivity.getPackageName() + "&hl=it")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select(".hAyfc .htlgb")
                        .get(7)
                        .ownText()
                newVersion
            } catch (e: Exception) {
                newVersion
            }
        }

    }

    private fun checkVersionUpdate() {
        val versionChecker = mActivity?.let { VersionChecker(it) }
        try {
            val latestVersion = versionChecker!!.execute().get()
            val appVersionName =
                BuildConfig.VERSION_NAME.replace("-DEBUG", "")
            if (latestVersion != null && !latestVersion.isEmpty()) {
                if (latestVersion != appVersionName) {
                    showUpdateDialog()
                } else {
                    nextPage()
                }
            } else {
                nextPage()
            }
            try {
                Log.d(
                    "update",
                    "Current version " + java.lang.Float.valueOf(appVersionName) + ", Playstore version " + java.lang.Float.valueOf(
                        latestVersion!!
                    )
                )
            } catch (e: Exception) {
                Log.d("", "checkVersionUpdate: Exception: " + e.message)
            }
        } catch (e: InterruptedException) {
            nextPage()
            e.printStackTrace()
        } catch (e: ExecutionException) {
            nextPage()
            e.printStackTrace()
        }
    }

    private fun nextPage() {
        Handler().postDelayed({

            if (SharedPreferencesEnum.getInstance(mActivity)!!
                    .getBoolean(SharedPreferencesEnum.Key.ISLOGIN)
            ) {
                startActivity(Intent(mActivity, HomeActivity::class.java))
                mActivity!!.finish()
            } else {
                navController!!.navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }, 2000)
    }

    fun showUpdateDialog() {
        val builder =
            AlertDialog.Builder(this!!.mActivity!!)
        builder.setTitle("New version available.")
        builder.setMessage("Update your app into latest version")
        builder.setIcon(R.drawable.ocd_logo)
        builder.setCancelable(false)
        builder.setPositiveButton(R.string.update_now,
            DialogInterface.OnClickListener { dialog, id ->
                val appName: String = mActivity!!.getPackageName()
                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$appName")
                        )
                    )
                } catch (anfe: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=$appName")
                        )
                    )
                }
            })
        builder.setNegativeButton(
            R.string.cancel
        ) { dialog, which ->
            nextPage()
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }


}