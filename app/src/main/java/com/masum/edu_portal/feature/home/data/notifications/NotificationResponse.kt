/*
 * *
 *  * Created by Md Masum Talukder on 5/8/20 10:28 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/6/20 12:30 AM
 *
 */
package com.masum.edu_portal.feature.home.data.notifications

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NotificationResponse {
    @SerializedName("nitification")
    @Expose
    var notification: List<Notification?>? = null


}