package com.masum.edu_portal.feature.auth.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 class LoginResponse constructor() {
    @SerializedName("access_token")
    @Expose
    var accessToken: String? = null

    @SerializedName("token_type")
    @Expose
    var tokenType: String? = null

    @SerializedName("expires_in")
    @Expose
    var expiresIn: Int? = null

    @SerializedName("user_info")
    @Expose
    var userInfo: UserInfo? = null

    @SerializedName("member_info")
    @Expose
    var memberInfo: String? = null

    @SerializedName("student_info")
    @Expose
    var studentInfo: String? = null

    @SerializedName("academic_info")
    @Expose
    var academicInfo: List<String>? = null

}