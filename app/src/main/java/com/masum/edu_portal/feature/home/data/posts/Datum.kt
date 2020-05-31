package com.masum.edu_portal.feature.home.data.posts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.masum.edu_portal.BuildConfig

class Datum {
    @SerializedName("image")
    @Expose
    var image: String? = null
    get() = BuildConfig.IMG_URL+field

    @SerializedName("post_id")
    @Expose
    var postId: String? = null

    @SerializedName("member_id")
    @Expose
    var memberId: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("post_details")
    @Expose
    var postDetails: String? = null

    @SerializedName("date_time")
    @Expose
    var dateTime: String? = null


    @SerializedName("designation")
    @Expose
    var designation: String? = null

    @SerializedName("memberimage")
    @Expose
    var memberimage: String? = null
        get() = BuildConfig.IMG_URL + field

    @SerializedName("office_name")
    @Expose
    var officeName: String? = null

    @SerializedName("full_name_english")
    @Expose
     var fullNameEnglish: String? = null

    @SerializedName("designation_name")
    @Expose
     var designationName: String? = null



}