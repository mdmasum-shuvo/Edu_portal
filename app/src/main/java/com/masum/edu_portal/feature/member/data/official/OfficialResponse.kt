package com.masum.edu_portal.feature.member.data.official

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OfficialResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

}