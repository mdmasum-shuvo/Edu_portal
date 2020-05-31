/*
 * *
 *  * Created by Md Masum Talukder on 5/16/20 7:23 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/11/20 2:05 AM
 *
 */
package com.masum.edu_portal.feature.home.data.designation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DesignationResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("designationInfo")
    @Expose
    var designationInfo: List<DesignationInfo>? = null

}