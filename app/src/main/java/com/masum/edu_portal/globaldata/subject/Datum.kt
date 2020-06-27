package com.masum.edu_portal.globaldata.subject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {
    @SerializedName("subject_id")
    @Expose
    var subjectId: Int? = null

    @SerializedName("subject_name")
    @Expose
    var subjectName: String? = null

    @SerializedName("short_name")
    @Expose
    var shortName: String? = null

    @SerializedName("created_by")
    @Expose
    var createdBy: String? = null

    @SerializedName("updated_by")
    @Expose
    var updatedBy: String? = null

    @SerializedName("picture")
    @Expose
    var picture: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

}