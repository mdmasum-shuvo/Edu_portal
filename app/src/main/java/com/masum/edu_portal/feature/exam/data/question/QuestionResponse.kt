package com.masum.edu_portal.feature.exam.data.question

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class QuestionResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

}