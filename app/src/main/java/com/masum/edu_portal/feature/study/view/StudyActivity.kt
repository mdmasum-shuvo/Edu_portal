package com.masum.edu_portal.feature.study.view

import android.os.Build
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseActivity
import com.masum.edu_portal.databinding.ActivityStudyBinding
import com.masum.edu_portal.di.ViewModelProviderFactory
import com.masum.edu_portal.feature.exam.adapter.ExamAdapter
import com.masum.edu_portal.feature.home.adapter.DashboardAdapter
import com.masum.edu_portal.feature.home.data.DashboardList
import com.masum.edu_portal.feature.member.adapter.ClassMateListAdapter
import com.masum.edu_portal.feature.study.adapter.AllClassAdapter
import com.masum.edu_portal.feature.study.adapter.AllVideoLectureAdapter
import com.masum.edu_portal.feature.study.adapter.LastClassLectureAdapter
import com.masum.edu_portal.myviewmodel.ClassViewModel
import com.masum.edu_portal.myviewmodel.GlobalViewModel
import com.masum.edu_portal.myviewmodel.StudyViewModel
import com.masum.edu_portal.utils.MyDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_home_dashboard.*
import javax.inject.Inject

class StudyActivity : BaseActivity() {
    private lateinit var binding: ActivityStudyBinding

    @Inject
    lateinit var studyViewModel: StudyViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_study
    }

    override fun initComponent() {
        binding = getBinding() as ActivityStudyBinding
        studyViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(StudyViewModel::class.java)
//        initToolbar()
       // enableBackButton()

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
    }

    override fun initFunctionality() {
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

                            /*      if (!classList.isEmpty()) {
                                      classList.clear()
                                  }


                                  binding.rv.adapter = classAdapter*/

                            var lastClassLectureAdapter =
                                LastClassLectureAdapter(this, dataResource.data!!.data!!.data)
                            binding.rvLastLecture.adapter = lastClassLectureAdapter


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

                            /*      if (!classList.isEmpty()) {
                                      classList.clear()
                                  }


                                  binding.rv.adapter = classAdapter*/


                            var allClassAdapter =
                                AllVideoLectureAdapter(this, dataResource.data!!.data!!.data)
                            binding.rvVideoLecture.adapter = allClassAdapter
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

                            /*      if (!classList.isEmpty()) {
                                      classList.clear()
                                  }
      */

                            var allClassAdapter =
                                AllClassAdapter(this, dataResource.data!!.data!!.data)
                            binding.rvAllLecture.adapter = allClassAdapter
                        }
                    }
                }
            }
        })
    }

    override fun onRefresh() {
    }

    override fun initListener() {
        callData()
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

}