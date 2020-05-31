package com.masum.edu_portal.feature.member.data.galleryinfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GalleryInfoResponse{
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("galleryInfo")
    @Expose
    private var galleryInfo: com.masum.edu_portal.feature.member.data.galleryinfo.GalleryInfo? = null

    fun getGalleryInfo(): com.masum.edu_portal.feature.member.data.galleryinfo.GalleryInfo? {
        return galleryInfo
    }

    fun setGalleryInfo(galleryInfo: com.masum.edu_portal.feature.member.data.galleryinfo.GalleryInfo?) {
        this.galleryInfo = galleryInfo
    }


}