package com.masum.edu_portal.repository

import com.masum.edu_portal.common.callback_listener.NetworkRequestCompleteCallback
import com.masum.edu_portal.feature.home.data.about.AboutU
import com.masum.edu_portal.common.callback_listener.HomeUserDataLoadListener
import com.masum.edu_portal.feature.home.data.about.AboutUsDataResponse
import com.masum.edu_portal.feature.home.data.notifications.Notification
import com.masum.edu_portal.feature.home.data.notifications.NotificationResponse
import com.masum.edu_portal.feature.home.data.posts.AllPostsResponse
import com.masum.edu_portal.feature.member.data.memberinfolist.Datum
import com.masum.edu_portal.feature.member.data.memberinfolist.MemberInfoListResponse
import com.masum.edu_portal.networks.HTTP_PARAM
import com.masum.edu_portal.utils.NetworkUtils
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository :
    HomeUserDataLoadListener {

    override fun getNotificationList(callback: NetworkRequestCompleteCallback<MutableList<Notification>>) {

        NetworkUtils.getApiService()!!.getNotifications()
            .enqueue(object : Callback<NotificationResponse> {
                override fun onFailure(call: Call<NotificationResponse>, t: Throwable) {
                    callback.onRequestFailed(t.message!!)
                }

                override fun onResponse(
                    call: Call<NotificationResponse>,
                    response: Response<NotificationResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()!!.notification != null) {
                            callback!!.onRequestSuccess(response.body()!!.notification as MutableList<Notification>)
                        } else {
                            callback!!.onRequestFailed("No Data Found")
                        }
                    } else {
                        callback!!.onRequestFailed(response.message())
                    }
                }

            })
    }


    override fun getMemberDataList(
        page: Int,
        callback: NetworkRequestCompleteCallback<MutableList<Datum>>
    ) {
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.PAGE_NUMBER, "" + page)
        NetworkUtils.getApiService()!!.getMemberListResponse(builder.build())
            .enqueue(object : Callback<MemberInfoListResponse> {
                override fun onResponse(
                    call: Call<MemberInfoListResponse>,
                    response: Response<MemberInfoListResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()!!.memberInfo!!.data != null) {
                            callback!!.onRequestSuccess(response.body()!!.memberInfo!!.data as MutableList<Datum>)
                        } else {
                            callback!!.onRequestFailed(response.body()!!.status!!)
                        }
                    } else {
                        callback!!.onRequestFailed(response.message())
                    }
                }

                override fun onFailure(call: Call<MemberInfoListResponse>, t: Throwable) {
                    t.message?.let { callback!!.onRequestFailed(it) }
                }
            })
    }

    override fun getStatusList(
        page: Int,
        callback: NetworkRequestCompleteCallback<MutableList<com.masum.edu_portal.feature.home.data.posts.Datum>>?
    ) {
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.PAGE_NUMBER, "" + page)

        NetworkUtils.getApiService()!!.getAllPosts().enqueue(object : Callback<AllPostsResponse> {
            override fun onFailure(call: Call<AllPostsResponse>, t: Throwable) {
                t.message?.let { callback!!.onRequestFailed(it) }
            }

            override fun onResponse(
                call: Call<AllPostsResponse>,
                response: Response<AllPostsResponse>
            ) {
                if (response.isSuccessful) {
                    callback!!.onRequestSuccess(response.body()!!.postInfo.data as MutableList<com.masum.edu_portal.feature.home.data.posts.Datum>)
                } else {
                    callback!!.onRequestFailed(response.message())
                }
            }
        })
    }


    override fun getAboutUsDataList(callback: NetworkRequestCompleteCallback<MutableList<AboutU>>) {

        NetworkUtils.getApiService()!!.getAboutUsData()
            .enqueue(object : Callback<AboutUsDataResponse> {
                override fun onFailure(call: Call<AboutUsDataResponse>, t: Throwable) {
                    callback.onRequestFailed(t.message!!)
                }

                override fun onResponse(
                    call: Call<AboutUsDataResponse>,
                    response: Response<AboutUsDataResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()!!.aboutUs != null) {
                            callback!!.onRequestSuccess(response.body()!!.aboutUs)
                        } else {
                            callback!!.onRequestFailed(response.body()!!.status)
                        }
                    } else {
                        callback!!.onRequestFailed(response.message())
                    }
                }

            })


    }
}