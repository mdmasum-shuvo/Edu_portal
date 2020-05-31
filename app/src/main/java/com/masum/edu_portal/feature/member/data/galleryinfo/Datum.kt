package com.masum.edu_portal.feature.member.data.galleryinfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum  {
    @SerializedName("gallery_id")
    @Expose
    var galleryId: String? = null

    @SerializedName("member_id")
    @Expose
    var memberId: String? = null

    @SerializedName("post_id")
    @Expose
    var postId: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("date_time")
    @Expose
    var dateTime: String? = null

}