package com.masum.edu_portal.feature.member.data.galleryinforesponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GalleryInfoResponse  {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("galleryInfo")
    @Expose
    private var galleryInfo: GalleryInfo? = null

    fun getGalleryInfo(): GalleryInfo? {
        return galleryInfo
    }

    fun setGalleryInfo(galleryInfo: GalleryInfo?) {
        this.galleryInfo = galleryInfo
    }

}