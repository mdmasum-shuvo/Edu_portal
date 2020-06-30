package com.masum.edu_portal.common

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.masum.edu_portal.R
import com.masum.edu_portal.di.MySharedPreferences
import com.masum.edu_portal.utils.AnimationUtility
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker
import com.treebo.internetavailabilitychecker.InternetConnectivityListener
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.fragment_home_dashboard.*
import kotlinx.android.synthetic.main.show_empty_view.*
import javax.inject.Inject

open abstract class BaseActivity : DaggerAppCompatActivity(), InternetConnectivityListener,  SwipeRefreshLayout.OnRefreshListener  {

    private var binding: ViewDataBinding? = null
    private var progressDialog: ProgressDialog? = null
    private var mToolbar: Toolbar? = null
    var mInternetAvailabilityChecker: InternetAvailabilityChecker? = null
    var mySharedPreferences: MySharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResourceFile())
        InternetAvailabilityChecker.init(this)

        initComponent()
        mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance()
        mInternetAvailabilityChecker!!.addInternetConnectivityListener(this)
        initFunctionality()
        initListener()

    }

    override fun onRefresh() {

    }
    protected abstract fun getLayoutResourceFile(): Int

    protected abstract fun initComponent()

    protected abstract fun initFunctionality()

    protected abstract fun initListener()

    open fun getBinding(): ViewDataBinding? {
        return binding
    }
    open fun initToolbar() {
        mToolbar =
            findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar)
    }

    open fun getToolbar(): Toolbar? {
        if (mToolbar == null) {
            mToolbar = findViewById(R.id.toolbar)
            setSupportActionBar(mToolbar)
        }
        return mToolbar
    }

    open fun enableBackButton() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }
    protected fun setNotificationBarBlackNWhite() {
        if (isBuildVersionOk) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
    open fun showErrorDialog(title: String?, message: String?) {
        val builder: androidx.appcompat.app.AlertDialog.Builder
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            androidx.appcompat.app.AlertDialog.Builder(this, R.style.DialogTheme)
        } else {
            androidx.appcompat.app.AlertDialog.Builder(this)
        }
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                android.R.string.ok
            ) { dialog, which -> dialog.dismiss() }
            .setIcon(R.drawable.sync)
            .show()
    }
    open fun hideEmptyView() {
        noDataView.setVisibility(View.GONE)
    }



    open fun startActivity(
        cls: Class<*>?,
        finishSelf: Boolean,
        bundle: Bundle?
    ) {
        val intent = Intent(this, cls)
        intent.putExtras(bundle!!)
        startActivity(intent)
        if (finishSelf) {
            finish()
        }
    }

    open fun showAlertDialog(title: String?, message: String?) {
        val builder: androidx.appcompat.app.AlertDialog.Builder
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            androidx.appcompat.app.AlertDialog.Builder(this, R.style.DialogTheme)
        } else {
            androidx.appcompat.app.AlertDialog.Builder(this)
        }
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                android.R.string.ok
            ) { dialog, which -> dialog.dismiss() }
            .setIcon(R.drawable.sync)
            .show()
    }


    open fun showFinishAlertDialog(title: String?, message: String?) {
        val builder: androidx.appcompat.app.AlertDialog.Builder
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            androidx.appcompat.app.AlertDialog.Builder(
                this,
                android.R.style.Theme_Material_Light_Dialog_Alert
            )
        } else {
            androidx.appcompat.app.AlertDialog.Builder(this)
        }
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                android.R.string.ok
            ) { dialog, which ->
                dialog.dismiss()
                finish()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }


    open fun logout() {
        val builder: AlertDialog.Builder
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AlertDialog.Builder(this, R.style.DialogTheme)
        } else {
            AlertDialog.Builder(this)
        }
        builder.setTitle(getString(R.string.logout_title))
        builder.setMessage(getString(R.string.logout_message))
        builder.setIcon(R.drawable.logout_icon)
        builder.setNegativeButton("No", null)
        builder.setPositiveButton(
            "Yes"
        ) { dialog: DialogInterface?, which: Int ->
            //  startActvity(this, LoginActivity.class, true);

            /*    val intent = Intent(this, Logi::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent.putExtra(AppConstants.PHONE_NUMBER, data))*/
        }
        val dialog = builder.create()
        dialog.show()
    }


    protected fun setNotificationBarTransparent() {
        if (isBuildVersionOk) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    protected val isBuildVersionOk: Boolean
        protected get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    protected fun setOnlyStatusBarTransparent() {
        if (isBuildVersionOk) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setEnterAnimation() {
        val slide = Slide(Gravity.BOTTOM)
        slide!!.addTarget(R.id.appbar)
        slide!!.addTarget(R.id.nested_Scroll)
        slide!!.interpolator = AnimationUtils.loadInterpolator(
            this
            , android.R.interpolator.decelerate_cubic
        )
        slide.duration = 300
        window.enterTransition = slide
    }

    fun showEmptyView() {
        noDataView.visibility = View.VISIBLE
        ll_internetstate_container.visibility = View.GONE
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun showLoader() {

        pullToRefresh.isRefreshing = true
        noDataView.visibility = View.GONE
        iv_no_wifi.visibility = View.GONE
        tv_internet_sate.visibility = View.VISIBLE
        tv_internet_sate.setText(this!!.getString(R.string.loading))
        ll_internetstate_container.visibility = View.VISIBLE
        consContainer.visibility = View.GONE
        tv_internet_sate.setAnimation(AnimationUtility.getFadeInAnimation(this))

        /*       consCommonView.setBackgroundColor(resources.getColor(R.color.colorBlack,mActivity!!.theme))
        consCommonView.alpha= .5F*/
    }

    open fun showProgressDialog() {
        this!!.runOnUiThread(Runnable {
            progressDialog =
                ProgressDialog.show(this, null, getString(R.string.please_wait), true)
        })
    }

    open fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing()) {
            this!!.runOnUiThread(Runnable { progressDialog!!.dismiss() })
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun hideLoader() {
        noDataView.visibility = View.GONE
        iv_no_wifi.visibility = View.GONE
        tv_internet_sate.visibility = View.GONE
        ll_internetstate_container.visibility = View.GONE
        consContainer.visibility = View.VISIBLE
        pullToRefresh.isRefreshing = false

        /*       consCommonView.setBackgroundColor(resources.getColor(R.color.colorBlack,mActivity!!.theme))
        consCommonView.alpha= .5F*/
    }
}