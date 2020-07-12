package com.masum.edu_portal.feature.study.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseActivity
import com.masum.edu_portal.common.Constant
import com.masum.edu_portal.common.callback_listener.ItemClickListener
import com.masum.edu_portal.databinding.ActivityStudyBinding
import com.masum.edu_portal.di.ViewModelProviderFactory
import com.masum.edu_portal.feature.member.view.ProfileActivity
import com.masum.edu_portal.feature.study.adapter.AllClassAdapter
import com.masum.edu_portal.feature.study.adapter.AllVideoLectureAdapter
import com.masum.edu_portal.feature.study.adapter.LastClassLectureAdapter
import com.masum.edu_portal.feature.study.data.all_study.Datum
import com.masum.edu_portal.myviewmodel.StudyViewModel
import com.masum.edu_portal.utils.MyDividerItemDecoration
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class StudyActivity : BaseActivity() {
    private lateinit var binding: ActivityStudyBinding
    private var allStudyList = ArrayList<Datum>()
    private var allLastLectureList = ArrayList<Datum>()
    private var allVideoLectureList = ArrayList<Datum>()
    private lateinit var lastClassLectureAdapter: LastClassLectureAdapter
    private lateinit var allVideoLectureAdapter: AllVideoLectureAdapter
    private lateinit var allClassLectureAdapter: AllClassAdapter

    @Inject
    lateinit var studyViewModel: StudyViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_study
    }

    override fun initComponent() {
        binding = getBinding() as ActivityStudyBinding
        binding.pullToRefresh.setOnRefreshListener(this)

        studyViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(StudyViewModel::class.java)
        initToolbar()
        enableBackButton()
        setToolbarTitle(getString(R.string.study))
        setOnlyStatusBarTransparent()
        setRecylerView()
    }

    private fun setRecylerView() {
        binding.rvAllLecture.setHasFixedSize(true)
        val layoutManagerH = LinearLayoutManager(
            this
            , LinearLayoutManager.HORIZONTAL
            , false
        )
        val layoutManagerH2 = LinearLayoutManager(
            this
            , LinearLayoutManager.HORIZONTAL
            , false
        )
        binding.rvAllLecture.layoutManager = layoutManagerH


        binding.rvVideoLecture.setHasFixedSize(true)
        binding.rvVideoLecture.layoutManager = layoutManagerH2



        binding.rvLastLecture.setHasFixedSize(true)
        val layoutManagerV = LinearLayoutManager(
            this
            , LinearLayoutManager.VERTICAL
            , false
        )
        binding.rvLastLecture.layoutManager = layoutManagerV
        binding.rvLastLecture.addItemDecoration(
            MyDividerItemDecoration(
                this!!,
                LinearLayoutManager.VERTICAL,
                16
            )
        )

        lastClassLectureAdapter = LastClassLectureAdapter(this, allLastLectureList)
        binding.rvLastLecture.adapter = lastClassLectureAdapter

        allClassLectureAdapter = AllClassAdapter(this, allStudyList)
        binding.rvAllLecture.adapter = allClassLectureAdapter

        allVideoLectureAdapter = AllVideoLectureAdapter(this, allVideoLectureList)
        binding.rvVideoLecture.adapter = allVideoLectureAdapter
    }

    override fun initFunctionality() {
        callData()
        observeAllLecture()
        observeAllVideoLecture()
        observeLastClassLecture()

    }

    private fun observeLastClassLecture() {
        studyViewModel.lastClassStudyDataList.observe(this, Observer { dataResource ->
            binding.pullToRefresh.isRefreshing = false
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            showLoader()
                            binding.pullToRefresh.isRefreshing = false

                        }
                    }
                    DataResource.DataStatus.ERROR -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            showErrorDialog("Failed!", dataResource.message)
                            hideLoader()
                        }
                    }
                    DataResource.DataStatus.SUCCESS -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            hideLoader()
                        }
                        if (dataResource.data!!.data != null) {

                            if (!allLastLectureList.isEmpty()) {
                                allLastLectureList.clear()
                            }
                            allLastLectureList.addAll(dataResource.data!!.data!!.data!!)
                            allLastLectureList.addAll(dataResource.data!!.data!!.data!!)
                            allLastLectureList.addAll(dataResource.data!!.data!!.data!!)
                            allLastLectureList.addAll(dataResource.data!!.data!!.data!!)
                            allLastLectureList.addAll(dataResource.data!!.data!!.data!!)
                            lastClassLectureAdapter.notifyDataSetChanged()


                        }
                    }
                }
            }
        })
    }

    private fun observeAllVideoLecture() {
        studyViewModel.videoStudyDataList.observe(this, Observer { dataResource ->
            binding.pullToRefresh.isRefreshing = false
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            showLoader()
                            binding.pullToRefresh.isRefreshing = false

                        }
                    }
                    DataResource.DataStatus.ERROR -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            showErrorDialog("Failed!", dataResource.message)
                            hideLoader()
                        }
                    }
                    DataResource.DataStatus.SUCCESS -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            hideLoader()
                        }
                        if (dataResource.data!!.data != null) {

                            if (!allVideoLectureList.isEmpty()) {
                                allVideoLectureList.clear()
                            }
                            allVideoLectureList.addAll(dataResource.data!!.data!!.data!!)
                            allVideoLectureList.addAll(dataResource.data!!.data!!.data!!)
                            allVideoLectureList.addAll(dataResource.data!!.data!!.data!!)
                            allVideoLectureList.addAll(dataResource.data!!.data!!.data!!)
                            allVideoLectureList.addAll(dataResource.data!!.data!!.data!!)
                            allVideoLectureAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }

    private fun observeAllLecture() {
        studyViewModel.allStudyDataList.observe(this, Observer { dataResource ->
            binding.pullToRefresh.isRefreshing = false
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            showLoader()
                            binding.pullToRefresh.isRefreshing = false
                        }
                    }
                    DataResource.DataStatus.ERROR -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            showErrorDialog("Failed!", dataResource.message)
                            hideLoader()
                        }
                    }
                    DataResource.DataStatus.SUCCESS -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            hideLoader()
                        }
                        if (dataResource.data!!.data != null) {
                            if (!allStudyList.isEmpty()) {
                                allStudyList.clear()
                            }
                            allStudyList.addAll(dataResource.data!!.data!!.data!!)
                            allStudyList.addAll(dataResource.data!!.data!!.data!!)
                            allStudyList.addAll(dataResource.data!!.data!!.data!!)
                            allStudyList.addAll(dataResource.data!!.data!!.data!!)
                            allStudyList.addAll(dataResource.data!!.data!!.data!!)
                            allClassLectureAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }

    override fun onRefresh() {
        callData()
    }

    override fun initListener() {

        allClassLectureAdapter.setOnItemClickListener(object : ItemClickListener {
            override fun onClick(position: Int, view: View?) {
                //toast("item" + position)
                when (view!!.id) {
                    R.id.ll_see_all_holder -> {
                        showToast("Limited Data")
                    }

                    else -> {
                        // showToast("Limited Data")

                        /*       var bundle = Bundle()
                               bundle.putSerializable(Constant.INTENT_KEY, allStudyList.get(position))
                               var intent = Intent(this@StudyActivity, ProfileActivity::class.java)
                               intent.putExtras(bundle)
                               startActivity(intent)*/
                    }
                }

            }
        })

        allVideoLectureAdapter.setOnItemClickListener(object : ItemClickListener {
            override fun onClick(position: Int, view: View?) {
                //toast("item" + position)
                when (view!!.id) {
                    R.id.ll_see_all_holder -> {
                        var intent = Intent(this@StudyActivity, ViewViewActivity::class.java)
                        //intent.putExtras(bundle)
                        startActivity(intent)
                    }

                    else -> {
                        // showToast("Limited Data")

                        /*     var bundle = Bundle()
                             bundle.putSerializable(Constant.INTENT_KEY, allStudyList.get(position))*/
                        var intent = Intent(this@StudyActivity, ViewViewActivity::class.java)
                        //intent.putExtras(bundle)
                        startActivity(intent)
                    }
                }

            }
        })


        lastClassLectureAdapter.setOnItemClickListener(object : ItemClickListener {
            override fun onClick(position: Int, view: View?) {
                //toast("item" + position)
                when (view!!.id) {

                }

            }
        })

        btnLogout.setOnClickListener {
            logout()
        }
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
    }

    private fun callData() {
        studyViewModel.getAllLecture()
        studyViewModel.getVideoLecture()
        studyViewModel.getLastClassLecture()
    }

    override fun onStop() {
        mInternetAvailabilityChecker!!.removeInternetConnectivityChangeListener(this);
        super.onStop()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // API 5+ solution
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}