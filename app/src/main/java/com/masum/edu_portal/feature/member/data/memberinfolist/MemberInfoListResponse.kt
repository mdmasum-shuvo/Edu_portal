package com.masum.edu_portal.feature.member.data.memberinfolist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MemberInfoListResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("memberInfo")
    @Expose
    var memberInfo: MemberInfo? = null

}