/*
 * *
 *  * Created by Md Masum Talukder on 5/9/20 1:41 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/9/20 1:41 AM
 *
 */

package com.masum.edu_portal.feature.home.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseFragment
import com.masum.edu_portal.databinding.FragmentPostBinding
import com.masum.edu_portal.feature.member.adapter.MemberPostAdapter
import kotlinx.android.synthetic.main.fragment_home_dashboard.*

class PostFragment : BaseFragment() {
    lateinit var binding: FragmentPostBinding

    private lateinit var memberPostAdapter: MemberPostAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate<FragmentPostBinding>(
                inflater,
                R.layout.fragment_post!!, container, false
            )

        mActivity = activity
        return binding!!.getRoot()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()

        callData()
        setRecylerView()

        clickListener()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun clickListener() {
        binding.search.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(mActivity).toBundle()
            val profileIntent = Intent(mActivity, UploadPostActivity::class.java)
            //findNavController(R.id.fragNavHost).navigate(R.id.memberDetailFragment)
            startActivity(profileIntent, bundle)
        }
    }

    override fun onRefresh() {
        super.onRefresh()
        pullToRefresh.isRefreshing = true
        callData()
    }

    private fun callData() {
    }


    private fun setRecylerView() {
        binding.rvpost.setHasFixedSize(true)
        val layoutManagerV = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
        binding.rvpost.layoutManager = layoutManagerV
    }

    override fun onStop() {
        mInternetAvailabilityChecker.removeInternetConnectivityChangeListener(this);
        super.onStop()
    }
}