package com.masum.edu_portal.feature.others


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
import com.masum.edu_portal.databinding.ActivityOthersBinding
import com.masum.edu_portal.di.ViewModelProviderFactory
import com.masum.edu_portal.feature.member.data.official.Datum
import com.masum.edu_portal.feature.others.adapter.AcademyOfficialAdapter
import com.masum.edu_portal.feature.others.adapter.TeacherAdapter
import com.masum.edu_portal.myviewmodel.AcademyViewModel
import com.masum.edu_portal.utils.MyDividerItemDecoration
import javax.inject.Inject

class OthersActivity : BaseActivity() {

    private lateinit var teacherAdapter: TeacherAdapter
    private lateinit var academyAdapter: AcademyOfficialAdapter
    private lateinit var binding: ActivityOthersBinding
    private var teacherList = ArrayList<Datum>()
    private var academyList = ArrayList<Datum>()

    @Inject
    lateinit var viewModel: AcademyViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_others
    }

    override fun initComponent() {
        binding = getBinding() as ActivityOthersBinding
        initToolbar()
        enableBackButton()
        setToolbarTitle("Academy/Official")
        setOnlyStatusBarTransparent()
        binding.pullToRefresh.setOnRefreshListener(this)
        viewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(AcademyViewModel::class.java)
        setRecylerView()
    }

    private fun setRecylerView() {
        val layoutManagerH = LinearLayoutManager(
            this
            , LinearLayoutManager.HORIZONTAL
            , false
        )
        binding.rvAcademy.setHasFixedSize(true)
        binding.rvAcademy.layoutManager = layoutManagerH

        binding.rvTeacher.setHasFixedSize(true)
        val layoutManagerV = LinearLayoutManager(
            this
            , LinearLayoutManager.VERTICAL
            , false
        )
        binding.rvTeacher.layoutManager = layoutManagerV
        binding.rvTeacher.addItemDecoration(
            MyDividerItemDecoration(
                this!!,
                LinearLayoutManager.VERTICAL,
                16
            )
        )

        academyAdapter = AcademyOfficialAdapter(this, academyList)
        binding.rvAcademy.adapter = academyAdapter

        teacherAdapter = TeacherAdapter(this, teacherList)
        binding.rvTeacher.adapter = teacherAdapter
    }


    override fun initFunctionality() {
        observeOfficialData()
        observeTeacherData()
    }

    override fun initListener() {
        academyAdapter.setOnItemClickListener(object : ItemClickListener {
            override fun onClick(position: Int, view: View?) {
                //toast("item" + position)
                when (view!!.id) {
                    R.id.ll_see_all_holder -> {
                        showToast("Limited Data")
                    }
                    R.id.btnPhone -> {
                    }
                    else -> {
              /*          var bundle = Bundle()
                        bundle.putSerializable(Constant.INTENT_KEY, classMateList.get(position))
                        var intent = Intent(activity, ProfileActivity::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)*/
                    }
                }

            }
        })

        teacherAdapter.setOnItemClickListener(object : ItemClickListener {
            override fun onClick(position: Int, view: View?) {
                //toast("item" + position)
            }
        })
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
        if (isConnected)
            callApi()
    }

    private fun callApi() {
        viewModel.getOfficialData()
        viewModel.getTeacherData()
    }

    private fun observeTeacherData() {
        viewModel.teacherLiveDataList.observe(this, Observer { dataResource ->
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
                            if (!teacherList.isEmpty()) {
                                teacherList.clear()
                            }
                            teacherList.addAll(dataResource.data!!.data!!.data!!)
                            teacherList.addAll(dataResource.data!!.data!!.data!!)
                            teacherList.addAll(dataResource.data!!.data!!.data!!)
                            teacherList.addAll(dataResource.data!!.data!!.data!!)
                            teacherList.addAll(dataResource.data!!.data!!.data!!)
                            teacherList.addAll(dataResource.data!!.data!!.data!!)
                            teacherAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }

    private fun observeOfficialData() {
        viewModel.officialLiveDataList.observe(this, Observer { dataResource ->
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
                            if (!academyList.isEmpty()) {
                                academyList.clear()
                            }
                            academyList.addAll(dataResource.data!!.data!!.data!!)
                            academyList.addAll(dataResource.data!!.data!!.data!!)
                            academyList.addAll(dataResource.data!!.data!!.data!!)
                            academyList.addAll(dataResource.data!!.data!!.data!!)
                            academyList.addAll(dataResource.data!!.data!!.data!!)
                            academyList.addAll(dataResource.data!!.data!!.data!!)
                            academyAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }


    override fun onRefresh() {
        super.onRefresh()
        callApi()
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