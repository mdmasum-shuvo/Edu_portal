package com.masum.edu_portal.feature.home.data.class_mate

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ClassMateResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

}