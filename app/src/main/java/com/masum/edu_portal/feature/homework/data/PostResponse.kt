package com.masum.edu_portal.feature.homework.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: String? = null

}