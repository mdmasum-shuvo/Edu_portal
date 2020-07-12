package com.masum.edu_portal.feature.auth.view


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseFragment
import com.masum.edu_portal.databinding.FragmentLoginBinding
import com.masum.edu_portal.di.ViewModelProviderFactory
import com.masum.edu_portal.feature.auth.datamodel.AuthResource
import com.masum.edu_portal.feature.auth.datamodel.LoginResponse
import com.masum.edu_portal.feature.home.view.HomeActivity
import com.masum.edu_portal.globaldata.organization.Datum
import com.masum.edu_portal.myviewmodel.AuthViewModel
import com.masum.edu_portal.myviewmodel.GlobalViewModel
import jrizani.jrspinner.JRSpinner
import javax.inject.Inject


class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewmodel: AuthViewModel
    private var organizationId:Int=-1

    @Inject
    lateinit var globalViewModel: GlobalViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate<FragmentLoginBinding>(
                inflater,
                R.layout.fragment_login!!, container, false
            )


        mActivity = activity
        return binding!!.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setOnlyStatusBarTransparent()
        }

        viewmodel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(AuthViewModel::class.java)

        binding.btnLogin.setOnClickListener({
            if (!isNetworkAvailable()){
                showErrorDialog(getString(R.string.noInterNet),getString(R.string.internet_not_available))
                return@setOnClickListener
            }
            if(isValid()){
                viewmodel.authentication(
                    binding.etUserName.text.toString(),
                    binding.etPassword.text.toString(),organizationId
                )
            }
        })

        subcribeAuthentcationObserver()
        observeOrganizationData()

    }

    fun isValid():Boolean{
        if ( binding.etUserName.text.toString().equals("") || binding.etPassword.text.toString().equals("")){
            return false
        }
    /*    else if(organizationId==-1){
            showErrorDialog("Error","Please select your institute")
            return false
        }*/
        return true
    }

    private fun observeOrganizationData() {
        globalViewModel.organization.observe(this, Observer { dataSource ->
            if (dataSource != null) {
                when (dataSource.status) {
                    DataResource.DataStatus.LOADING -> {
                        showProgressDialog()
                    }
                    DataResource.DataStatus.ERROR -> {
                        hideProgressDialog()
                    }
                    DataResource.DataStatus.SUCCESS -> {
                        hideProgressDialog()
                        addOrganizationToSpinner(dataSource.data!!.data)
                    }
                }
            }
        })
    }

    private fun addOrganizationToSpinner(data: List<Datum>?) {

        var orgList = arrayOfNulls<String>(data!!.size)
        var orgIdIdList = arrayOfNulls<Int>(data!!.size)
        for (i in 0 until data!!.size) {
            try {
                orgList[i] = data!![i].orgName
                orgIdIdList[i] = data!![i].orgId!!
            } catch (e: Exception) {
            }
        }

        binding.spnOrg.setMultiple(false)
        binding.spnOrg.setItems(orgList)
        binding.spnOrg.setOnItemClickListener(JRSpinner.OnItemClickListener { position ->
            organizationId = data[position].orgId!!
        })
        binding.spnOrg.setText("South Assign College")

    }


    private fun subcribeAuthentcationObserver() {
        viewmodel.observeLogin()
            .observe(this, Observer<AuthResource<LoginResponse>> { userAuthResource ->
                if (userAuthResource != null) {
                    when (userAuthResource.status) {

                        AuthResource.AuthStatus.LOADING -> {
                            showProgressDialog()
                        }
                        AuthResource.AuthStatus.ERROR -> {
                            hideProgressDialog()
                            Log.e("data", userAuthResource.message)
                        }
                        AuthResource.AuthStatus.AUTHENTICATED -> {
                            startActivity(Intent(activity, HomeActivity::class.java))
                            Log.e("data", userAuthResource.data!!.accessToken)
                            hideProgressDialog()
                        }
                        AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        }
                    }
                }
            })
    }


}


