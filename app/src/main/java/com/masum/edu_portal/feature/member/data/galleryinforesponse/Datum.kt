package com.masum.edu_portal.feature.member.data.galleryinforesponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum  {
    @SerializedName("gallery_id")
    @Expose
    var galleryId: String? = null

    @SerializedName("gallery_title_id")
    @Expose
    var galleryTitleId: String? = null

    @SerializedName("member_id")
    @Expose
    var memberId: String? = null

    @SerializedName("post_id")
    @Expose
    var postId: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("date_time")
    @Expose
    var dateTime: String? = null

    @SerializedName("gallery_title")
    @Expose
    var galleryTitle: String? = null



}