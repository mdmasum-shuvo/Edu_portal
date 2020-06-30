package com.masum.edu_portal.feature.member.data.profile.attendance

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AttendanceResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<Datum>? =
        null

}