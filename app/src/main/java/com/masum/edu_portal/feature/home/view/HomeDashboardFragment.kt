/*
 * *
 *  * Created by Md Masum Talukder on 5/4/20 2:47 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/4/20 2:42 AM
 *
 */

package com.masum.edu_portal.feature.home.view

import android.content.Intent
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
import com.example.myzoomlibrary.ZoomMainActivity
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseFragment
import com.masum.edu_portal.common.Constant
import com.masum.edu_portal.common.callback_listener.ItemClickListener
import com.masum.edu_portal.databinding.FragmentHomeDashboardBinding
import com.masum.edu_portal.di.ViewModelProviderFactory
import com.masum.edu_portal.feature.exam.adapter.ExamAdapter
import com.masum.edu_portal.feature.exam.view.ExamActivity
import com.masum.edu_portal.feature.home.adapter.DashboardAdapter
import com.masum.edu_portal.feature.home.data.DashboardList
import com.masum.edu_portal.feature.home.data.class_mate.Datum
import com.masum.edu_portal.feature.homework.view.HomeWorkActivity
import com.masum.edu_portal.feature.member.adapter.ClassMateListAdapter
import com.masum.edu_portal.feature.member.view.ProfileActivity
import com.masum.edu_portal.feature.myclass.view.MyClassActivity
import com.masum.edu_portal.feature.others.OthersActivity
import com.masum.edu_portal.feature.study.view.StudyActivity
import com.masum.edu_portal.myviewmodel.ClassViewModel
import com.masum.edu_portal.myviewmodel.ExamViewModel
import com.masum.edu_portal.myviewmodel.StudentViewModel
import com.masum.edu_portal.utils.MyDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_home_dashboard.*
import java.util.*
import javax.inject.Inject

class HomeDashboardFragment : BaseFragment() {

    lateinit var binding: FragmentHomeDashboardBinding
    private lateinit var dashAdapter: DashboardAdapter
    private lateinit var classMateAdapter: ClassMateListAdapter
    private lateinit var examAdapter: ExamAdapter

    private lateinit var viewmodel: StudentViewModel
    private lateinit var examViewModel: ExamViewModel
    private lateinit var classViewModel: ClassViewModel
    private var classMateList = ArrayList<Datum>()
    private var examList = ArrayList<com.masum.edu_portal.feature.exam.data.exam.Datum>()

    private lateinit var meetingId: String
    private lateinit var meetingPass: String

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
        examViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(ExamViewModel::class.java)

        classViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(ClassViewModel::class.java)
        // callNetworkData()
        setRecylerView()
        initListener()
        setAdapterListener()
        functionality()

    }

    private fun callNetworkData() {
        viewmodel.getClassMateData()
        examViewModel.getUpComingExamData()
        classViewModel.getUpComingClassData()
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
        classMateAdapter = ClassMateListAdapter(mActivity, classMateList)

        binding.rvnewsFeed.setHasFixedSize(true)
        val layoutManagerV = LinearLayoutManager(
            mActivity
            , LinearLayoutManager.VERTICAL
            , false
        )
        binding.rvnewsFeed.layoutManager = layoutManagerV
        binding.rvnewsFeed.addItemDecoration(
            MyDividerItemDecoration(
                activity!!,
                LinearLayoutManager.VERTICAL,
                16
            )
        )

        examAdapter = ExamAdapter(mActivity, examList)
        binding.rvnewsFeed.adapter = examAdapter


        val gridLayout = GridLayoutManager(mActivity, 3)

        var dashboard = DashboardList(mActivity)
        dashAdapter = DashboardAdapter(mActivity, dashboard.list)
        rvDashboard.layoutManager = LinearLayoutManager(mActivity)
        rvDashboard.layoutManager = gridLayout
        rvDashboard.setAdapter(dashAdapter)
        dashAdapter.notifyDataSetChanged()
    }

    private fun functionality() {
        observeClassMateData()
        observeExamData()
        obServeClassData()
/*        btn_live_class.setOnClickListener {

            var bundle=Bundle()
            bundle.putString("id",meetingId)
            bundle.putString("pass","User Name")
            var intent=Intent(activity,ZoomMainActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }*/
    }

    private fun obServeClassData() {
        classViewModel.classMateList.observe(this, Observer { dataResource ->
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {

                    }
                    DataResource.DataStatus.ERROR -> {

                    }
                    DataResource.DataStatus.SUCCESS -> {
                        if (dataResource.data!!.data != null) {
                            meetingId = dataResource.data!!.data!!.get(0)!!.zoomUser!!
                            meetingPass = dataResource.data!!.data!!.get(0)!!.zoomPass!!

                        }
                    }
                }
            }
        })
    }

    private fun observeExamData() {
        examViewModel.examList.observe(this, Observer { dataResource ->
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            showLoader()
                        }
                    }
                    DataResource.DataStatus.ERROR -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            // showErrorDialog("Failed!", dataResource.message)
                            hideLoader()
                            binding.tvFeed.visibility = View.GONE
                        }
                    }

                    DataResource.DataStatus.SUCCESS -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            hideLoader()
                        }
                        if (dataResource.data!!.data != null) {
                            if (!examList.isEmpty()) {
                                examList.clear()
                            }
                            binding.tvFeed.visibility = View.VISIBLE
                            examList.addAll(dataResource.data!!.data!!)
                            examAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
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
                            showErrorDialog("Failed!", dataResource.message)
                            hideLoader()
                            binding.tvClassMate.visibility = View.GONE
                        }
                    }
                    DataResource.DataStatus.SUCCESS -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            hideLoader()
                        }
                        if (dataResource.data!!.data!!.data != null) {
                            if (!classMateList.isEmpty()) {
                                classMateList.clear()
                            }
                            binding.tvClassMate.visibility = View.VISIBLE
                            classMateList.addAll(dataResource.data!!.data!!.data!!)
                            classMateList.addAll(dataResource.data!!.data!!.data!!)
                            classMateList.addAll(dataResource.data!!.data!!.data!!)
                            classMateList.addAll(dataResource.data!!.data!!.data!!)
                            classMateList.addAll(dataResource.data!!.data!!.data!!)
                            classMateList.addAll(dataResource.data!!.data!!.data!!)
                            classMateList.addAll(dataResource.data!!.data!!.data!!)
                            classMateList.addAll(dataResource.data!!.data!!.data!!)
                            classMateList.addAll(dataResource.data!!.data!!.data!!)
                            classMateList.addAll(dataResource.data!!.data!!.data!!)
                            classMateList.addAll(dataResource.data!!.data!!.data!!)
                            classMateList.addAll(dataResource.data!!.data!!.data!!)
                            classMateAdapter.notifyDataSetChanged()
                            binding.rvMemberList.adapter = classMateAdapter
                        }
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
                when (position) {
                    0 -> {
                        startActivity(Intent(activity, MyClassActivity::class.java))
                    }

                    1 -> {
                        startActivity(Intent(activity, AttendanceActivity::class.java))
                    }
                    2 -> {
                        startActivity(Intent(activity, ExamActivity::class.java))
                    }
                    3 -> {
                        startActivity(Intent(activity, HomeWorkActivity::class.java))
                    }
                    4 -> {
                        startActivity(Intent(activity, StudyActivity::class.java))
                    }
                    5 -> {
                        startActivity(Intent(activity, OthersActivity::class.java))
                    }
                }

            }
        })


        classMateAdapter.setOnItemClickListener(object : ItemClickListener {
            override fun onClick(position: Int, view: View?) {
                //toast("item" + position)
                when (view!!.id) {
                    R.id.ll_see_all_holder -> {
                        toast("Limited Data")
                    }
                    R.id.btnPhone -> {
                    }
                    else -> {
                        var bundle = Bundle()
                        bundle.putSerializable(Constant.INTENT_KEY, classMateList.get(position))
                        var intent = Intent(activity, ProfileActivity::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                }

            }
        })

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onInternetConnectivityChanged(isConnected: Boolean) {
        super.onInternetConnectivityChanged(isConnected)
        if (isConnected) {
            callNetworkData()
        }
    }

    override fun onStop() {
        mInternetAvailabilityChecker.removeInternetConnectivityChangeListener(this);
        super.onStop()
    }
}


