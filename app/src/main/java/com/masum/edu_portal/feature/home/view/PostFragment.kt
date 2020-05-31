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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseFragment
import com.masum.edu_portal.common.callback_listener.HomeUserDataLoadListener
import com.masum.edu_portal.databinding.FragmentPostBinding
import com.masum.edu_portal.feature.home.data.posts.Datum
import com.masum.edu_portal.feature.member.adapter.MemberPostAdapter
import com.masum.edu_portal.repository.HomeRepository
import com.masum.edu_portal.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.fragment_home_dashboard.*

class PostFragment : BaseFragment() {
    lateinit var binding: FragmentPostBinding

    private lateinit var memberPostAdapter: MemberPostAdapter
    private lateinit var viewModel: MyViewModel
    private lateinit var listener: HomeUserDataLoadListener
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
        listener = HomeRepository()
        viewModel = ViewModelProviders.of(mActivity as HomeActivity).get(MyViewModel::class.java)
        callData()
        setRecylerView()
        setLiveDataListener()

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
        viewModel.getAllPostList(1, listener);
    }

    private fun setLiveDataListener() {

        viewModel.allMemberPost.observe(
            this,
            object : Observer<List<Datum>> {
                override fun onChanged(data: List<Datum>) {
                    memberPostAdapter = mActivity?.let { MemberPostAdapter(it, data) }!!
                    binding.rvpost.adapter = memberPostAdapter


/*
                    memberPostAdapter.setOnItemClickListener(object : ItemClickListener {
                        override fun onClick(position: Int, view: View?) {
                            showToast("click on position: " + position)
                        }

                    })
*/
                }
            })


        viewModel.isProgressLoad.observe(this, object : Observer<Boolean> {
            override fun onChanged(isProgressLoad: Boolean) {
                Log.e("", "")
                if (isProgressLoad) {
                    Log.e("", "")
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showLoader()
                    }

                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        hideLoader()
                    }
                }

            }
        })


        viewModel.isDataLoadFailed.observe(this, object : Observer<String> {
            override fun onChanged(errorText: String) {

            }
        })
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