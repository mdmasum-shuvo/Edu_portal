package com.masum.edu_portal.feature.exam.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ExamListResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

}