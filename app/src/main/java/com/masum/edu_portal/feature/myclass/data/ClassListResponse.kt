package com.masum.edu_portal.feature.myclass.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ClassListResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<Datum>? =
        null

}