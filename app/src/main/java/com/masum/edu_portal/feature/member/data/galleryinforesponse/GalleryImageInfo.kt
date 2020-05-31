package com.masum.edu_portal.feature.member.data.galleryinforesponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GalleryImageInfo{
    @SerializedName("current_page")
    @Expose
    var currentPage: String? = null

    @SerializedName("data")
    @Expose
    private var data: List<Datum>? = null

    @SerializedName("first_page_url")
    @Expose
    var firstPageUrl: String? = null

    @SerializedName("from")
    @Expose
    var from: String? = null

    @SerializedName("last_page")
    @Expose
    var lastPage: String? = null

    @SerializedName("last_page_url")
    @Expose
    var lastPageUrl: String? = null

    @SerializedName("next_page_url")
    @Expose
    var nextPageUrl: String? = null

    @SerializedName("path")
    @Expose
    var path: String? = null

    @SerializedName("per_page")
    @Expose
    var perPage: String? = null

    @SerializedName("prev_page_url")
    @Expose
    var prevPageUrl: String? = null

    @SerializedName("to")
    @Expose
    var to: String? = null

    @SerializedName("total")
    @Expose
    var total: String? = null

    fun getData(): List<Datum>? {
        return data
    }

    fun setData(data: List<Datum>?) {
        this.data = data
    }


}