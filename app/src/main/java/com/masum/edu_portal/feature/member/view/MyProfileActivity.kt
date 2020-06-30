package com.masum.edu_portal.feature.member.view

import android.os.Build
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseActivity
import com.masum.edu_portal.databinding.ActivityMyProfileBinding
import com.masum.edu_portal.di.SessionManager
import com.masum.edu_portal.di.ViewModelProviderFactory
import com.masum.edu_portal.feature.exam.adapter.ExamAdapter
import com.masum.edu_portal.feature.member.adapter.AttendanceAdapter
import com.masum.edu_portal.feature.member.data.profile.attendance.Datum
import com.masum.edu_portal.myviewmodel.ProfileViewModel
import com.masum.edu_portal.utils.MyDividerItemDecoration
import java.util.ArrayList
import javax.inject.Inject

class MyProfileActivity : BaseActivity() {
    @Inject
    lateinit var sessionManager: SessionManager
    private lateinit var binding: ActivityMyProfileBinding
    private lateinit var viewmodel: ProfileViewModel
    private var attendanceList = ArrayList<Datum>()
    private lateinit var attendanceAdapter: AttendanceAdapter

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_my_profile
    }

    override fun initComponent() {
        binding = getBinding() as ActivityMyProfileBinding
        setNotificationBarTransparent()
        setNotificationBarBlackNWhite()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setEnterAnimation()
        }
        viewmodel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(ProfileViewModel::class.java)

        setRecylerView()
    }

    private fun setRecylerView() {
        val layoutManagerV = LinearLayoutManager(
            this
            , LinearLayoutManager.VERTICAL
            , false
        )
        binding.rv.layoutManager = layoutManagerV
        binding.rv.addItemDecoration(
            MyDividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL,
                16
            )
        )
        attendanceAdapter = AttendanceAdapter(this, attendanceList)
        binding.rv.adapter = attendanceAdapter
    }

    override fun initFunctionality() {
        binding.data = sessionManager.getAuthUser().value!!.data!!.userInfo
        observeAttendanceHistory()
    }

    private fun observeAttendanceHistory() {
        viewmodel.attendanceHistoryData.observe(this, Observer { dataResource ->
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            showProgressDialog()
                        }
                    }
                    DataResource.DataStatus.ERROR -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            showErrorDialog("Failed!", dataResource.message)
                            hideProgressDialog()
                        }
                    }

                    DataResource.DataStatus.SUCCESS -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            hideProgressDialog()
                        }
                        if (dataResource.data!!.data != null) {
                            if (!attendanceList.isEmpty()) {
                                attendanceList.clear()
                            }
                            attendanceList.addAll(dataResource.data!!.data!!)
                            attendanceAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }

    override fun initListener() {
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
        if (isConnected) {
            viewmodel.getAttendanceHistory()
        }
    }

    fun upArrowClicked(view: View) {
        onBackPressed()
    }
}