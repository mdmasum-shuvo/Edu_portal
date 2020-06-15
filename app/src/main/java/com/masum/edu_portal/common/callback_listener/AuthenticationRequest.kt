/*
 * *
 *  * Created by Md Masum Talukder on 5/8/20 6:04 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/8/20 6:04 AM
 *
 */

package com.masum.edu_portal.common.callback_listener

import okhttp3.MultipartBody

interface AuthenticationRequest {
    fun authentication(dataBuilder: MultipartBody.Builder,callback: NetworkRequestCompleteCallback<Member>)

}