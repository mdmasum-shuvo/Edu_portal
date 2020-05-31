package com.masum.edu_portal.feature.member.data.galleryinforesponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TitleInfo  {
    @SerializedName("gallery_title")
    @Expose
    var galleryTitle: String? = null

    @SerializedName("member_id")
    @Expose
    var memberId: String? = null

    @SerializedName("post_id")
    @Expose
    var postId: String? = null

    @SerializedName("gallery_title_id")
    @Expose
    var galleryTitleId: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null


}