/*
 * *
 *  * Created by Md Masum Talukder on 5/8/20 6:08 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/8/20 6:08 AM
 *
 */
package com.masum.edu_portal.feature.auth.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("member")
    @Expose
    var member: Member? = null

}