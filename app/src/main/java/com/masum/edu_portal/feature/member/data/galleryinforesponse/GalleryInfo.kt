package com.masum.edu_portal.feature.member.data.galleryinforesponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GalleryInfo{
    @SerializedName("title_info")
    @Expose
    private var titleInfo: List<TitleInfo?>? = null

    @SerializedName("gallery_image_info")
    @Expose
    private var galleryImageInfo: List<GalleryImageInfo?>? = null
    fun getTitleInfo(): List<TitleInfo?>? {
        return titleInfo
    }

    fun setTitleInfo(titleInfo: List<TitleInfo?>?) {
        this.titleInfo = titleInfo
    }

    fun getGalleryImageInfo(): List<GalleryImageInfo?>? {
        return galleryImageInfo
    }

    fun setGalleryImageInfo(galleryImageInfo: List<GalleryImageInfo?>?) {
        this.galleryImageInfo = galleryImageInfo
    }


}