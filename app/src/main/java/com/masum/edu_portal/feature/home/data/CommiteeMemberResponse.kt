/*
 * *
 *  * Created by Md Masum Talukder on 5/16/20 7:15 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/6/20 12:30 AM
 *
 */
package com.masum.edu_portal.feature.home.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.masum.edu_portal.feature.member.data.memberinfolist.Datum

class CommiteeMemberResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("committeememberInfo")
    @Expose
    var committeememberInfo: List<Datum>? =
        null

}