/*
 * *
 *  * Created by Md Masum Talukder on 5/16/20 7:21 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/10/20 9:19 PM
 *
 */
package com.masum.edu_portal.feature.home.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NotificaitonSaveResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: String? = null

}