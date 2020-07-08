package com.masum.edu_portal.feature.study.data.last_lecture

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LastLectureResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

}