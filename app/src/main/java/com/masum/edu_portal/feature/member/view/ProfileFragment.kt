package com.masum.edu_portal.feature.member.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseFragment
import com.masum.edu_portal.databinding.FragmentProfileBinding

class ProfileFragment :BaseFragment() {



    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate<FragmentProfileBinding>(
                inflater,
                R.layout.fragment_profile!!, container, false
            )

        mActivity = activity
        return binding!!.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initListener()
    }

    override fun onStop() {
      //  mInternetAvailabilityChecker.removeInternetConnectivityChangeListener(this);
        super.onStop()
    }
}