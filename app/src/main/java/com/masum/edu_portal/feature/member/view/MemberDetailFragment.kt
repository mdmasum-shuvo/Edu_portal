package com.masum.edu_portal.feature.member.view

import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseFragment
import com.masum.edu_portal.databinding.FragmentMemberDetailBinding

class MemberDetailFragment :BaseFragment(){

    lateinit var binding:FragmentMemberDetailBinding

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setEnterAnimation()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate<FragmentMemberDetailBinding>(
                inflater,
                R.layout.fragment_member_detail!!, container, false
            )

        mActivity = activity
        return binding!!.getRoot()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setEnterAnimation() {
        val slide = Slide(Gravity.BOTTOM)
        slide!!.addTarget(R.id.appbar)
        slide!!.addTarget(R.id.nested_Scroll)
        slide!!.interpolator = AnimationUtils.loadInterpolator(
            activity
            , android.R.interpolator.decelerate_cubic
        )
        slide.duration = 300
        activity!!.window.enterTransition = slide
    }

}