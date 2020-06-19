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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseFragment
import com.masum.edu_portal.common.callback_listener.ItemClickListener
import com.masum.edu_portal.databinding.FragmentHomeDashboardBinding
import com.masum.edu_portal.feature.home.adapter.DashboardAdapter
import com.masum.edu_portal.feature.home.data.DashboardList
import com.masum.edu_portal.feature.member.data.memberinfolist.Datum
import kotlinx.android.synthetic.main.fragment_home_dashboard.*

class HomeDashboardFragment : BaseFragment() {

    lateinit var binding: FragmentHomeDashboardBinding
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

        setRecylerView()
        initListener()

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


    fun callMemberList(memberList: List<Datum>) {
        Log.e("", "")

    }



    override fun onRefresh() {
        super.onRefresh()
        consContainer.visibility = View.GONE
        pullToRefresh.isRefreshing = true
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



}


