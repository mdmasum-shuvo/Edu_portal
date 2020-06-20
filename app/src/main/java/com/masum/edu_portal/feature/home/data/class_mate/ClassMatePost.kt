package com.masum.edu_portal.feature.home.data.class_mate

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ClassMatePost {
    @SerializedName("org_id")
    @Expose
    var orgId: Int? = null

    @SerializedName("class_id")
    @Expose
    var classId: Int? = null

    @SerializedName("group_id")
    @Expose
    var groupId: Int? = null

    @SerializedName("section_id")
    @Expose
    var sectionId: Int? = null

    @SerializedName("current_page")
    @Expose
    var currentPage: Int? = null

}