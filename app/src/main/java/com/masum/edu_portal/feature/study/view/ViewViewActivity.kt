package com.masum.edu_portal.feature.study.view

import android.os.Build
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseActivity
import com.masum.edu_portal.common.callback_listener.ItemClickListener
import com.masum.edu_portal.databinding.ActivityViewViewBinding
import com.masum.edu_portal.di.ViewModelProviderFactory
import com.masum.edu_portal.feature.study.adapter.VideoLectureVerticalAdapter
import com.masum.edu_portal.feature.study.data.all_study.Datum
import com.masum.edu_portal.myviewmodel.StudyViewModel
import com.masum.edu_portal.utils.MyDividerItemDecoration
import javax.inject.Inject

class ViewViewActivity : BaseActivity() {
    private lateinit var binding: ActivityViewViewBinding
    private var allVideoLectureList = ArrayList<Datum>()
    private lateinit var videoAdapter: VideoLectureVerticalAdapter

    @Inject
    lateinit var studyViewModel: StudyViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_view_view
    }

    override fun initComponent() {
        binding = getBinding() as ActivityViewViewBinding
        initToolbar()
        enableBackButton()
        setToolbarTitle("Video Lecture")
        setOnlyStatusBarTransparent()
        binding.pullToRefresh.setOnRefreshListener(this)

        studyViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(StudyViewModel::class.java)
        setRecylerView()
    }

    private fun setRecylerView() {
        binding.rv.setHasFixedSize(true)
        val layoutManagerV = LinearLayoutManager(
            this
            , LinearLayoutManager.VERTICAL
            , false
        )
        binding.rv.layoutManager = layoutManagerV
        binding.rv.addItemDecoration(
            MyDividerItemDecoration(
                this!!,
                LinearLayoutManager.VERTICAL,
                16
            )
        )

        videoAdapter = VideoLectureVerticalAdapter(this, allVideoLectureList)
        binding.rv.adapter = videoAdapter
    }

    override fun initFunctionality() {

        observeAllVideoLecture()
    }

    override fun initListener() {
        videoAdapter.setOnItemClickListener(object : ItemClickListener {
            override fun onClick(position: Int, view: View?) {
                //toast("item" + position)


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
                            videoAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }


    override fun onInternetConnectivityChanged(isConnected: Boolean) {
        if (isConnected)
            studyViewModel.getVideoLecture()

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