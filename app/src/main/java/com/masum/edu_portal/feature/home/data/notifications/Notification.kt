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

class Notification {
    @SerializedName("notification_id")
    @Expose
    private var notificationId: Int? = null

    @SerializedName("category_type")
    @Expose
    private var categoryType: String? = null

    @SerializedName("title")
    @Expose
    private var title: String? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("deliverd_by")
    @Expose
    private var deliverdBy: String? = null

    @SerializedName("attachment")
    @Expose
    private var attachment: String? = null

    @SerializedName("deliverd_date_time")
    @Expose
    private var deliverdDateTime: String? = null

    @SerializedName("button_show")
    @Expose
    private var buttonShow: Int? = null

    @SerializedName("created_at")
    @Expose
    private var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    private var updatedAt: String? = null

    fun getNotificationId(): Int? {
        return notificationId
    }

    fun setNotificationId(notificationId: Int?) {
        this.notificationId = notificationId
    }

    fun getCategoryType(): String? {
        return categoryType
    }

    fun setCategoryType(categoryType: String?) {
        this.categoryType = categoryType
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getDeliverdBy(): String? {
        return deliverdBy
    }

    fun setDeliverdBy(deliverdBy: String?) {
        this.deliverdBy = deliverdBy
    }

    fun getAttachment(): String? {
        return attachment
    }

    fun setAttachment(attachment: String?) {
        this.attachment = attachment
    }

    fun getDeliverdDateTime(): String? {
        return deliverdDateTime
    }

    fun setDeliverdDateTime(deliverdDateTime: String?) {
        this.deliverdDateTime = deliverdDateTime
    }

    fun getButtonShow(): Int? {
        return buttonShow
    }

    fun setButtonShow(buttonShow: Int?) {
        this.buttonShow = buttonShow
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String?) {
        this.createdAt = createdAt
    }

    fun getUpdatedAt(): String? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: String?) {
        this.updatedAt = updatedAt
    }

}