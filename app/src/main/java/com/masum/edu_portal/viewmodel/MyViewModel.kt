package com.masum.edu_portal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.masum.edu_portal.common.callback_listener.AuthenticationRequest
import com.masum.edu_portal.common.callback_listener.NetworkRequestCompleteCallback
import com.masum.edu_portal.feature.home.data.about.AboutU
import com.masum.edu_portal.common.callback_listener.HomeUserDataLoadListener

import com.masum.edu_portal.feature.auth.datamodel.Member
import com.masum.edu_portal.feature.home.data.notifications.Notification
import com.masum.edu_portal.feature.member.data.memberinfolist.Datum
import okhttp3.MultipartBody

open class MyViewModel : ViewModel() {

    var aboutUsMutableDataList = MutableLiveData<List<AboutU>>()
    var memberMutableDataList = MutableLiveData<List<Datum>>()
    var authData = MutableLiveData<Member>()
    var allMemberPost = MutableLiveData<List<com.masum.edu_portal.feature.home.data.posts.Datum>>()
    var allNotification = MutableLiveData<List<Notification>>()
    var isDataLoadFailed = MutableLiveData<String>()
    var isProgressLoad = MutableLiveData<Boolean>()


    fun checkAuthentication(dataBuilder: MultipartBody.Builder, call: AuthenticationRequest) {
        isProgressLoad.postValue(true)

        call.authentication(dataBuilder, object : NetworkRequestCompleteCallback<Member> {
            override fun onRequestSuccess(data: Member) {
                isProgressLoad.postValue(false)
                authData.postValue(data)
            }

            override fun onRequestFailed(errorText: String) {
                isDataLoadFailed.postValue(errorText)
                isProgressLoad.postValue(false)
            }
        })
    }

    fun getAllNotification(call: HomeUserDataLoadListener) {
        isProgressLoad.postValue(true)

        call.getNotificationList( object : NetworkRequestCompleteCallback<List<Notification>> {
            override fun onRequestSuccess(data: List<Notification>) {
                isProgressLoad.postValue(false)
                allNotification.postValue(data)
            }

            override fun onRequestFailed(errorText: String) {
                isDataLoadFailed.postValue(errorText)
                isProgressLoad.postValue(false)
            }
        })
    }


    fun getAboutUsDataList(call: HomeUserDataLoadListener) {
        isProgressLoad.postValue(true)
        call.getAboutUsDataList(object :
            NetworkRequestCompleteCallback<List<AboutU>> {
            override fun onRequestSuccess(data: List<AboutU>) {
                aboutUsMutableDataList.postValue(data)
                isProgressLoad.postValue(false)
            }

            override fun onRequestFailed(errorText: String) {
                isDataLoadFailed.postValue(errorText)
                isProgressLoad.postValue(false)
            }
        })
    }


//member

    fun getMemberList(pageNumber: Int, callback: HomeUserDataLoadListener) {
        isProgressLoad.postValue(true)
        callback.getMemberDataList(pageNumber,
            object : NetworkRequestCompleteCallback<List<Datum>> {
                override fun onRequestSuccess(data: List<Datum>) {
                    memberMutableDataList.postValue(data)
                    isProgressLoad.postValue(false)
                }

                override fun onRequestFailed(errorText: String) {
                    isDataLoadFailed.postValue(errorText)
                    isProgressLoad.postValue(false)
                }

            })

    }

    fun getAllPostList(pageNumber: Int, call: HomeUserDataLoadListener) {
        isProgressLoad.postValue(true)
        call.getStatusList(pageNumber,
            object :
                NetworkRequestCompleteCallback<List<com.masum.edu_portal.feature.home.data.posts.Datum>> {
                override fun onRequestSuccess(data: List<com.masum.edu_portal.feature.home.data.posts.Datum>) {
                    allMemberPost.postValue(data)
                    isProgressLoad.postValue(false)

                }

                override fun onRequestFailed(errorText: String) {
                    isDataLoadFailed.postValue(errorText)
                    isProgressLoad.postValue(false)
                }

            })

    }




}