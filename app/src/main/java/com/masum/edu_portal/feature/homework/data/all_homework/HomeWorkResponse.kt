package com.masum.edu_portal.feature.homework.data.all_homework

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeWorkResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

}