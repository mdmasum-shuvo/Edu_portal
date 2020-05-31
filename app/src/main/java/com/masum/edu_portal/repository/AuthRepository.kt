/*
 * *
 *  * Created by Md Masum Talukder on 5/8/20 6:04 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/8/20 6:04 AM
 *
 */

package com.masum.edu_portal.repository

import com.masum.edu_portal.common.callback_listener.AuthenticationRequest
import com.masum.edu_portal.common.callback_listener.NetworkRequestCompleteCallback
import com.masum.edu_portal.feature.auth.datamodel.LoginResponse
import com.masum.edu_portal.feature.auth.datamodel.Member
import com.masum.edu_portal.utils.NetworkUtils
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository : AuthenticationRequest {
    override fun authentication(
        dataBuilder: MultipartBody.Builder,
        callback: NetworkRequestCompleteCallback<Member>
    ) {
        NetworkUtils.getApiService()!!.authentication(dataBuilder.build())
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    callback.onRequestFailed(t.message!!)
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()!!.member != null) {
                            callback.onRequestSuccess(response.body()!!.member!!)
                        } else {
                            callback.onRequestFailed(response.body()!!.status!!)
                        }
                    } else {
                        callback.onRequestFailed(response.message())
                    }
                }
            })
    }
}