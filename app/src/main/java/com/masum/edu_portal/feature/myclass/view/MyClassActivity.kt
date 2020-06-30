package com.masum.edu_portal.feature.myclass.view

import android.os.Build
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseActivity
import com.masum.edu_portal.databinding.ActivityMyClassBinding
import com.masum.edu_portal.di.ViewModelProviderFactory
import com.masum.edu_portal.feature.myclass.adapter.TodaysClassAdapter
import com.masum.edu_portal.feature.myclass.data.Datum
import com.masum.edu_portal.myviewmodel.ClassViewModel
import kotlinx.android.synthetic.main.activity_my_class.*
import java.util.ArrayList
import javax.inject.Inject

class MyClassActivity : BaseActivity() {
    private lateinit var classViewModel: ClassViewModel
    private var isNetWOrkConnected = true;
    private var classList = ArrayList<Datum>()
    lateinit var binding: ActivityMyClassBinding
    private lateinit var classAdapter: TodaysClassAdapter

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_my_class
    }

    override fun initComponent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setOnlyStatusBarTransparent()
        }
        binding= getBinding() as ActivityMyClassBinding
        setRecylerView()
        classViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(ClassViewModel::class.java)
    }

    override fun initFunctionality() {
        classViewModel.getUpComingClassData()
        obServeClassData()
    }

    private fun setRecylerView() {
        binding.rv.setHasFixedSize(true)
        val layoutManagerH = LinearLayoutManager(
            this
            , LinearLayoutManager.HORIZONTAL
            , false
        )
        binding.rv.layoutManager = layoutManagerH
        classAdapter = TodaysClassAdapter(this, classList)
    }

    override fun initListener() {
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
        isNetWOrkConnected = isConnected
        if (isConnected) {
        }
    }

    override fun onRefresh() {
        if (isNetWOrkConnected){
            pullToRefresh.isRefreshing=true
            classViewModel.getUpComingClassData()
        }
        else{
            pullToRefresh.isRefreshing=false
        }
    }

    private fun obServeClassData(){
        classViewModel.classMateList.observe(this, Observer { dataResource ->
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
                            if (!classList.isEmpty()) {
                                classList.clear()
                            }
                            classList.addAll(dataResource.data!!.data!!)
                            classAdapter.notifyDataSetChanged()
                            binding.rv.adapter = classAdapter
                        }
                    }
                }
            }
        })
    }

    override fun onStop() {
        mInternetAvailabilityChecker!!.removeInternetConnectivityChangeListener(this);
        super.onStop()
    }
}