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
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseFragment
import com.masum.edu_portal.databinding.FragmentLoginBinding
import com.masum.edu_portal.di.ViewModelProviderFactory
import com.masum.edu_portal.feature.auth.datamodel.AuthResource
import com.masum.edu_portal.feature.auth.datamodel.LoginResponse
import com.masum.edu_portal.feature.home.view.HomeActivity
import com.masum.edu_portal.myviewmodel.AuthViewModel
import javax.inject.Inject


class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewmodel: AuthViewModel

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
            viewmodel.authentication(
                binding.etUserName.text.toString(),
                binding.etPassword.text.toString()
            )
        })

        subcribeAuthentcationObserver()
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


