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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseFragment
import com.masum.edu_portal.common.callback_listener.ItemClickListener
import com.masum.edu_portal.databinding.FragmentHomeDashboardBinding
import com.masum.edu_portal.di.ViewModelProviderFactory
import com.masum.edu_portal.feature.home.adapter.DashboardAdapter
import com.masum.edu_portal.feature.home.data.DashboardList
import com.masum.edu_portal.feature.member.adapter.ClassMateListAdapter
import com.masum.edu_portal.myviewmodel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_home_dashboard.*
import javax.inject.Inject

class HomeDashboardFragment : BaseFragment() {

    lateinit var binding: FragmentHomeDashboardBinding
    private lateinit var dashAdapter: DashboardAdapter
    private lateinit var classMateAdapter: ClassMateListAdapter

    private lateinit var viewmodel: StudentViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
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

        viewmodel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(StudentViewModel::class.java)
        viewmodel.getClassMateData()
        setRecylerView()
        initListener()
        setAdapterListener()
        functionality()
        observeClassMateData()

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

        val gridLayout = GridLayoutManager(mActivity, 3)

        var dashboard = DashboardList(mActivity)
        dashAdapter = DashboardAdapter(mActivity, dashboard.list)
        rvDashboard.layoutManager = LinearLayoutManager(mActivity)
        rvDashboard.layoutManager = gridLayout
        rvDashboard.setAdapter(dashAdapter)
        dashAdapter.notifyDataSetChanged()
    }

    private fun functionality() {

    }


    private fun observeClassMateData() {
        viewmodel.classMateList.observe(this, Observer { dataResource ->
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            showLoader()
                        }
                    }
                    DataResource.DataStatus.ERROR -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            hideLoader()
                        }
                    }
                    DataResource.DataStatus.SUCCESS -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            hideLoader()
                        }
                        classMateAdapter =
                            ClassMateListAdapter(mActivity, dataResource.data!!.data!!.data)
                        binding.rvMemberList.adapter = classMateAdapter
                    }
                }
            }
        })
    }

    override fun onRefresh() {
        super.onRefresh()
        consContainer.visibility = View.GONE
        pullToRefresh.isRefreshing = true
        viewmodel.getClassMateData()

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


