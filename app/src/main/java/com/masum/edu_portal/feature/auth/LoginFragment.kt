package com.masum.edu_portal.feature.auth


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseFragment
import com.masum.edu_portal.databinding.FragmentLoginBinding


class LoginFragment : BaseFragment()  {

    private lateinit var binding: FragmentLoginBinding

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
    }




}


