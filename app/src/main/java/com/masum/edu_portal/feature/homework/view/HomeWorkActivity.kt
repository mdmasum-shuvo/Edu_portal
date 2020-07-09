package com.masum.edu_portal.feature.homework.view

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
import com.masum.edu_portal.databinding.ActivityHomeWorkBinding
import com.masum.edu_portal.di.ViewModelProviderFactory
import com.masum.edu_portal.feature.homework.adapter.AllHomeWorkAdapter
import com.masum.edu_portal.feature.homework.adapter.NewHomeWorkAdapter
import com.masum.edu_portal.feature.homework.data.all_homework.Datum
import com.masum.edu_portal.myviewmodel.HomeWorkViewModel
import com.masum.edu_portal.utils.MyDividerItemDecoration
import kotlinx.android.synthetic.main.toolbar.*
import java.util.ArrayList
import javax.inject.Inject

class HomeWorkActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeWorkBinding
    private lateinit var allHomeWorkAdapter: AllHomeWorkAdapter
    private lateinit var newHomeworkAdapter: NewHomeWorkAdapter
    private lateinit var viewmodel: HomeWorkViewModel
    private var allHomeWorkList = ArrayList<Datum>()
    private var newHomeWorkList = ArrayList<Datum>()

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_home_work
    }

    override fun initComponent() {
        binding = getBinding() as ActivityHomeWorkBinding
        binding.pullToRefresh.setOnRefreshListener(this)
        setOnlyStatusBarTransparent()
        initToolbar()
        enableBackButton()
        setToolbarTitle(getString(R.string.homework))
        viewmodel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(HomeWorkViewModel::class.java)
        setRecylerView()
    }

    override fun initFunctionality() {
        callData()
        observeAllHomeworkData()
        observeNewHomeworkData()
    }

    private fun observeNewHomeworkData() {
        viewmodel.allHomeworkList.observe(this, Observer { dataResource ->
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
                        }
                    }
                    DataResource.DataStatus.SUCCESS -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            hideLoader()
                        }
                        if (dataResource.data!!.data != null) {
                            if (!newHomeWorkList.isEmpty()) {
                                newHomeWorkList.clear()
                            }

                            newHomeWorkList.addAll(dataResource.data!!.data!!.data!!)
                            newHomeWorkList.addAll(dataResource.data!!.data!!.data!!)
                            newHomeWorkList.addAll(dataResource.data!!.data!!.data!!)
                            newHomeWorkList.addAll(dataResource.data!!.data!!.data!!)
                            newHomeWorkList.addAll(dataResource.data!!.data!!.data!!)
                            newHomeWorkList.addAll(dataResource.data!!.data!!.data!!)
                            newHomeWorkList.addAll(dataResource.data!!.data!!.data!!)
                            newHomeWorkList.addAll(dataResource.data!!.data!!.data!!)
                            newHomeWorkList.addAll(dataResource.data!!.data!!.data!!)
                            newHomeworkAdapter.notifyDataSetChanged()

                        }

                    }
                }
            }
        })
    }

    private fun observeAllHomeworkData() {
        viewmodel.allHomeworkList.observe(this, Observer { dataResource ->
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
                        }
                    }
                    DataResource.DataStatus.SUCCESS -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            hideLoader()
                        }
                        if (dataResource.data!!.data != null) {
                            if (!allHomeWorkList.isEmpty()) {
                                allHomeWorkList.clear()
                            }

                            allHomeWorkList.addAll(dataResource.data!!.data!!.data!!)
                            allHomeWorkList.addAll(dataResource.data!!.data!!.data!!)
                            allHomeWorkList.addAll(dataResource.data!!.data!!.data!!)
                            allHomeWorkList.addAll(dataResource.data!!.data!!.data!!)
                            allHomeWorkAdapter.notifyDataSetChanged()

                        }

                    }
                }
            }
        })

    }

    private fun callData() {
        viewmodel.getAllHomework()
        viewmodel.getNewHomework()
    }

    override fun initListener() {

        allHomeWorkAdapter.setOnItemClickListener(object : ItemClickListener {
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


        newHomeworkAdapter.setOnItemClickListener(object : ItemClickListener {
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

        btnLogout.setOnClickListener{
            logout()
        }

    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
    }

    override fun onRefresh() {
        callData()

    }

    private fun setRecylerView() {
        binding.rvAllHomework.setHasFixedSize(true)
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
        binding.rvAllHomework.layoutManager = layoutManagerH
        allHomeWorkAdapter = AllHomeWorkAdapter(this, allHomeWorkList)
        binding.rvAllHomework.adapter = allHomeWorkAdapter
        binding.rvNewHomework.setHasFixedSize(true)
        binding.rvNewHomework.layoutManager = layoutManagerH2

        newHomeworkAdapter = NewHomeWorkAdapter(this, newHomeWorkList)
        binding.rvNewHomework.adapter = newHomeworkAdapter


        binding.rvPendingHomework.setHasFixedSize(true)
        val layoutManagerV = LinearLayoutManager(
            this
            , LinearLayoutManager.VERTICAL
            , false
        )
        binding.rvPendingHomework.layoutManager = layoutManagerV
        binding.rvPendingHomework.addItemDecoration(
            MyDividerItemDecoration(
                this!!,
                LinearLayoutManager.VERTICAL,
                16
            )
        )
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