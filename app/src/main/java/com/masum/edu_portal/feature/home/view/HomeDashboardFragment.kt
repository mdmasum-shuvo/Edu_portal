/*
 * *
 *  * Created by Md Masum Talukder on 5/4/20 2:47 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/4/20 2:42 AM
 *
 */

package com.masum.edu_portal.feature.home.view


import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseFragment
import com.masum.edu_portal.common.callback_listener.ItemClickListener
import com.masum.edu_portal.databinding.FragmentHomeDashboardBinding
import com.masum.edu_portal.feature.home.data.about.AboutU
import com.masum.edu_portal.common.callback_listener.HomeUserDataLoadListener
import com.masum.edu_portal.feature.home.adapter.DashboardAdapter
import com.masum.edu_portal.feature.home.adapter.NotificationAdapter
import com.masum.edu_portal.feature.home.callbackListener.HomeCallBackListener
import com.masum.edu_portal.feature.home.data.DashboardList
import com.masum.edu_portal.feature.home.data.notifications.Notification
import com.masum.edu_portal.feature.member.adapter.MemberListAdapter
import com.masum.edu_portal.feature.member.data.memberinfolist.Datum
import com.masum.edu_portal.repository.HomeRepository
import kotlinx.android.synthetic.main.fragment_home_dashboard.*

class HomeDashboardFragment : BaseFragment() {

    lateinit var binding: FragmentHomeDashboardBinding
    private lateinit var callBackListener1: HomeCallBackListener
    private lateinit var viewModel: MyViewModel
    private lateinit var listener: HomeUserDataLoadListener
    private var memberPageNumber: Int = 1
    private lateinit var memberAdapter: MemberListAdapter
    private lateinit var dashAdapter: DashboardAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate<FragmentHomeDashboardBinding>(
                inflater,
                R.layout.fragment_home_dashboard!!, container, false
            )
        mActivity = activity
        return binding!!.getRoot()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener = HomeRepository()
        viewModel = ViewModelProviders.of(mActivity as HomeActivity).get(MyViewModel::class.java)
        setRecylerView()
        initListener()
        callData()
        callLiveDataListener()
        setAdapterListener()
        functionality()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setRecylerView() {

        binding.rvMemberList.setHasFixedSize(true)
        val layoutManagerH = LinearLayoutManager(
            mActivity
            , LinearLayoutManager.HORIZONTAL
            , false
        )
        binding.rvMemberList.layoutManager = layoutManagerH

        binding.rvnewsFeed.setHasFixedSize(true)
        val layoutManagerV = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
        binding.rvnewsFeed.layoutManager = layoutManagerV

        val gridLayout =GridLayoutManager(mActivity,3)

        var dashboard = DashboardList(mActivity)
        dashAdapter = DashboardAdapter(mActivity, dashboard.list)
        rvDashboard.layoutManager = LinearLayoutManager(mActivity)
        rvDashboard.layoutManager =gridLayout
        rvDashboard.setAdapter(dashAdapter)
        dashAdapter.notifyDataSetChanged()
    }

    private fun functionality() {

    }

    private fun callData() {
        viewModel.getAboutUsDataList(listener);
        viewModel.getMemberList(memberPageNumber, listener);
        viewModel.getAllNotification(listener);
    }

    fun callMemberList(memberList: List<Datum>) {
        Log.e("", "")

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun callLiveDataListener() {
        viewModel.aboutUsMutableDataList.observe(this, object : Observer<List<AboutU>> {
            override fun onChanged(data: List<AboutU>) {
                Log.e("", "")

            }
        })

        viewModel.allNotification.observe(this, object : Observer<List<Notification>> {
            override fun onChanged(data: List<Notification>) {

                var adapter = NotificationAdapter(mActivity!!, data)
                rvnewsFeed.adapter = adapter

            }
        })

        viewModel.memberMutableDataList.observe(this, object : Observer<List<Datum>> {
            override fun onChanged(data: List<Datum>) {
                memberAdapter = mActivity?.let { MemberListAdapter(it, data) }!!
                binding.rvMemberList.adapter = memberAdapter
                //memberAdapter.notifyDataSetChanged()

                memberAdapter.setOnItemClickListener(object : ItemClickListener {
                    override fun onClick(position: Int, view: View?) {
                        showToast("click on position: " + position)
                    }

                })
            }
        })


        viewModel.isProgressLoad.observe(this, object : Observer<Boolean> {
            override fun onChanged(isProgressLoad: Boolean) {
                Log.e("", "")
                if (isProgressLoad) {
                    Log.e("", "")
                    showLoader()

                } else {
                    hideLoader()
                }

            }
        })


        viewModel.isDataLoadFailed.observe(this, object : Observer<String> {
            override fun onChanged(errorText: String) {

            }
        })
    }


    override fun onRefresh() {
        super.onRefresh()
        consContainer.visibility = View.GONE
        pullToRefresh.isRefreshing = true
        callData()
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setAdapterListener() {
        dashAdapter.setOnItemClickListener(object : ItemClickListener {
            override fun onClick(position: Int, view: View?) {
                /*        val extras = FragmentNavigatorExtras(
                            img to "header_image"
                        )
                        view!!.findNavController().navigate(
                            R.id.memberDetailFragment,
                            null, // Bundle of args
                            null, // NavOptions
                            extras
                        )*/

            }
        })

    }

    override fun onStop() {
        mInternetAvailabilityChecker.removeInternetConnectivityChangeListener(this);
        super.onStop()
    }

    fun setCallBackListener(callBackListener: HomeCallBackListener) {
        callBackListener1 = callBackListener
        Log.e("", "")
    }

}


