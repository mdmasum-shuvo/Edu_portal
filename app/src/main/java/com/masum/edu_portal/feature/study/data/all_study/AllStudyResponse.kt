package com.masum.edu_portal.feature.study.data.all_study

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AllStudyResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

}