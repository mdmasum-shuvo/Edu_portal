package com.masum.edu_portal.feature.exam.data.exam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.masum.edu_portal.feature.exam.data.exam.Datum

class ExamListResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

}