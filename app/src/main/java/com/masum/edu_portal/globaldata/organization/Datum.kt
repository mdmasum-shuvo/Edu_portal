package com.masum.edu_portal.globaldata.organization

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {
    @SerializedName("org_id")
    @Expose
    var orgId: Int? = null

    @SerializedName("org_name")
    @Expose
    var orgName: String? = null

}