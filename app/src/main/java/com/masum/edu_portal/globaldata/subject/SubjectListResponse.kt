package com.masum.edu_portal.globaldata.subject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SubjectListResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

}