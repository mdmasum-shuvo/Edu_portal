package com.masum.edu_portal.feature.exam.view

import android.os.Build
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseActivity
import com.masum.edu_portal.common.callback_listener.ItemClickListener
import com.masum.edu_portal.databinding.ActivityExamBinding
import com.masum.edu_portal.di.ViewModelProviderFactory
import com.masum.edu_portal.feature.exam.adapter.ExamAdapter
import com.masum.edu_portal.feature.exam.data.exam.Datum
import com.masum.edu_portal.globaldata.adapter.SubjectAdapter
import com.masum.edu_portal.myviewmodel.ExamViewModel
import com.masum.edu_portal.myviewmodel.GlobalViewModel
import com.masum.edu_portal.utils.MyDividerItemDecoration
import kotlinx.android.synthetic.main.activity_exam.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.ArrayList
import javax.inject.Inject

class ExamActivity : BaseActivity() {
    private lateinit var binding: ActivityExamBinding
    private lateinit var globalViewModel: GlobalViewModel
    private lateinit var examViewModel: ExamViewModel
    private var examList = ArrayList<Datum>()
    private var subjectList = ArrayList<com.masum.edu_portal.globaldata.subject.Datum>()
    private lateinit var examAdapter: ExamAdapter
    private lateinit var subjectAdapter: SubjectAdapter

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_exam
    }

    override fun initComponent() {
        binding = getBinding() as ActivityExamBinding
        binding.pullToRefresh.setOnRefreshListener(this)
        globalViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(GlobalViewModel::class.java)
        examViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(ExamViewModel::class.java)
        setOnlyStatusBarTransparent()
        initToolbar()
        enableBackButton()
        setToolbarTitle(getString(R.string.exam))
        setRecyclerView()
    }

    override fun initFunctionality() {
       callData()

        observeSubjectData()
        observeExamData()
    }

    private fun callData() {
        globalViewModel.getSubjectList()
        examViewModel.getUpComingExamData()
    }

    override fun onRefresh() {
        callData()
    }

    override fun initListener() {
        btnLogout.setOnClickListener{
            logout()
        }


        subjectAdapter.setOnItemClickListener(object : ItemClickListener {
            override fun onClick(position: Int, view: View?) {
                showToast("item" + position)
            }
        })

        examAdapter.setOnItemClickListener(object : ItemClickListener {
            override fun onClick(position: Int, view: View?) {
                showToast("item" + position)
            }
        })
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
    }

    private fun setRecyclerView() {
        val gridLayout = GridLayoutManager(this, 3)

        rv_subject.layoutManager = LinearLayoutManager(this)
        rv_subject.layoutManager = gridLayout

        subjectAdapter = SubjectAdapter(this, subjectList)
        rv_subject.adapter = subjectAdapter

        binding.rvnewsFeed.setHasFixedSize(true)
        val layoutManagerV = LinearLayoutManager(
            this
            , LinearLayoutManager.VERTICAL
            , false
        )
        binding.rvnewsFeed.layoutManager = layoutManagerV
        binding.rvnewsFeed.addItemDecoration(
            MyDividerItemDecoration(
                this!!,
                LinearLayoutManager.VERTICAL,
                16
            )
        )

        examAdapter = ExamAdapter(this, examList)
        binding.rvnewsFeed.adapter = examAdapter
    }

    private fun observeSubjectData() {
        globalViewModel.subjectLiveData.observe(this, Observer { dataResource ->
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {

                    }
                    DataResource.DataStatus.ERROR -> {

                    }

                    DataResource.DataStatus.SUCCESS -> {

                        if (dataResource.data!!.data != null) {
                            if (!subjectList.isEmpty()) {
                                subjectList.clear()
                            }
                            subjectList.addAll(dataResource.data!!.data!!)
                            subjectAdapter.notifyDataSetChanged()

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
                            examList.addAll(dataResource.data!!.data!!)
                            examList.addAll(dataResource.data!!.data!!)
                            examList.addAll(dataResource.data!!.data!!)
                            examList.addAll(dataResource.data!!.data!!)
                            examList.addAll(dataResource.data!!.data!!)
                            examList.addAll(dataResource.data!!.data!!)
                            examList.addAll(dataResource.data!!.data!!)
                            examList.addAll(dataResource.data!!.data!!)
                            examList.addAll(dataResource.data!!.data!!)
                            examList.addAll(dataResource.data!!.data!!)
                            examList.addAll(dataResource.data!!.data!!)
                            examList.addAll(dataResource.data!!.data!!)
                            examList.addAll(dataResource.data!!.data!!)
                            examList.addAll(dataResource.data!!.data!!)
                            examList.addAll(dataResource.data!!.data!!)
                            examAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
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