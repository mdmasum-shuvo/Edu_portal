package com.masum.edu_portal.common

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.masum.edu_portal.R
import com.masum.edu_portal.utils.AnimationUtility
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker
import com.treebo.internetavailabilitychecker.InternetConnectivityListener
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home_dashboard.*
import kotlinx.android.synthetic.main.show_empty_view.*
import java.util.*

open abstract class BaseFragment : DaggerFragment(), InternetConnectivityListener,
    SwipeRefreshLayout.OnRefreshListener {
    private var binding: ViewDataBinding? = null
    var mActivity: Activity? = null
    lateinit var mInternetAvailabilityChecker: InternetAvailabilityChecker
    var isManualRefreshing = false
    var isInternetConnected = false
    private var progressDialog: ProgressDialog? = null
    private var mToolbar: Toolbar? = null

    private var mDatePickerDialog: DatePickerDialog? = null
    private var mTimePicker: TimePickerDialog? = null

    fun initListener() {
        pullToRefresh.setOnRefreshListener(this)
        InternetAvailabilityChecker.init(mActivity)
        mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance()
        mInternetAvailabilityChecker!!.addInternetConnectivityListener(this)


    }


    open fun getBinding(): ViewDataBinding? {
        return binding
    }

    override fun onRefresh() {
        isManualRefreshing = true

    }

    fun showToast(msg: String) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_LONG).show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onInternetConnectivityChanged(isConnected: Boolean) {
        isInternetConnected = isConnected
        if (isConnected) {
            pullToRefresh.isRefreshing = true
            showLoader()
        } else {
            showInterNotAvailableView()
        }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    protected fun setOnlyStatusBarTransparent() {

        mActivity!!.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        mActivity!!.window.statusBarColor = Color.TRANSPARENT
    }


    fun showEmptyView() {
        noDataView.visibility = View.VISIBLE
        ll_internetstate_container.visibility = View.GONE
    }

    open fun startActivity(
        cls: Class<*>?,
        finishSelf: Boolean,
        bundle: Bundle?
    ) {
        val intent = Intent(activity, cls)
        intent.putExtras(bundle!!)
        startActivity(intent)
        if (finishSelf) {
            activity!!.finish()
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun showLoader() {

        pullToRefresh.isRefreshing = true
        noDataView.visibility = View.GONE
        iv_no_wifi.visibility = View.GONE
        tv_internet_sate.visibility = View.VISIBLE
        tv_internet_sate.setText(mActivity!!.getString(R.string.loading))
        ll_internetstate_container.visibility = View.VISIBLE
        consContainer.visibility = View.GONE
        tv_internet_sate.setAnimation(AnimationUtility.getFadeInAnimation(mActivity))

        /*       consCommonView.setBackgroundColor(resources.getColor(R.color.colorBlack,mActivity!!.theme))
        consCommonView.alpha= .5F*/
    }

    open fun showProgressDialog() {
        mActivity!!.runOnUiThread(Runnable {
            progressDialog =
                ProgressDialog.show(mActivity, null, getString(R.string.please_wait), true)
        })
    }

    open fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing()) {
            mActivity!!.runOnUiThread(Runnable { progressDialog!!.dismiss() })
        }
    }

    open fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            mActivity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        var activeNetworkInfo: NetworkInfo? = null
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.activeNetworkInfo
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
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

    fun showInterNotAvailableView() {
        ll_internetstate_container.visibility = View.VISIBLE
        noDataView.visibility = View.GONE
        iv_no_wifi.visibility = View.VISIBLE
        tv_internet_sate.visibility = View.VISIBLE
        tv_internet_sate.setText(mActivity!!.getString(R.string.noInterNet))
        consContainer.visibility = View.GONE
    }

    open fun showAlertDialog(title: String?, message: String?) {
        val builder: AlertDialog.Builder
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AlertDialog.Builder(mActivity as Activity, R.style.DialogTheme)
        } else {
            AlertDialog.Builder(mActivity as Activity)
        }
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                android.R.string.ok
            ) { dialog, which -> dialog.dismiss() }
            .setIcon(R.drawable.tick)
            .show()
    }

    open fun showFinishAlertDialog(title: String?, message: String?) {
        val builder: AlertDialog.Builder
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AlertDialog.Builder(mActivity as Activity, R.style.DialogTheme)
        } else {
            AlertDialog.Builder(mActivity as Activity)
        }
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                android.R.string.ok
            ) { dialog, which ->
                dialog.dismiss()
                mActivity!!.finish()
            }
            .setIcon(R.drawable.tick)
            .show()
    }

    open fun setDatePicker(editText: EditText) {
        val newCalendar = Calendar.getInstance()
        mDatePickerDialog = DatePickerDialog(
            mActivity as Activity,
            OnDateSetListener { view, year, monthOfYear, dayOfMonth -> editText.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year) },
            newCalendar[Calendar.YEAR],
            newCalendar[Calendar.MONTH],
            newCalendar[Calendar.DAY_OF_MONTH]
        )
        mDatePickerDialog!!.getDatePicker()
        //      mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        mDatePickerDialog!!.show()
    }

    open fun toast(s: String) {
        Toast.makeText(mActivity, "" + s, Toast.LENGTH_SHORT).show()
    }

    open fun setTimePicker(editText: EditText) {
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
        val minute = mcurrentTime[Calendar.MINUTE]
        mTimePicker = TimePickerDialog(
            mActivity as Activity,
            OnTimeSetListener { timePicker, selectedHour, selectedMinute -> editText.setText("$selectedHour:$selectedMinute") },
            hour,
            minute,
            true
        ) //Yes 24 hour time
        mTimePicker!!.setTitle("Select Time")
        mTimePicker!!.show()
    }


    open fun showErrorDialog(title: String?, message: String?) {
        val builder: AlertDialog.Builder
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AlertDialog.Builder(mActivity as Activity, R.style.DialogTheme)
        } else {
            AlertDialog.Builder(mActivity as Activity)
        }
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                android.R.string.ok
            ) { dialog, which -> dialog.dismiss() }
            .setIcon(R.drawable.sync)
            .show()
    }
}