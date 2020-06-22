package com.masum.edu_portal.globaldata.organization

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrgListResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<Datum>? =
        null

}