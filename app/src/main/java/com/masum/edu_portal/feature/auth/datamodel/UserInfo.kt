package com.masum.edu_portal.feature.auth.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserInfo {
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("confirmation_code")
    @Expose
    var confirmationCode: String? = null

    @SerializedName("confirmed")
    @Expose
    var confirmed: Int? = null

    @SerializedName("is_active")
    @Expose
    var isActive: Int? = null

    @SerializedName("activated_by")
    @Expose
    var activatedBy: String? = null

    @SerializedName("deactivated_by")
    @Expose
    var deactivatedBy: String? = null

    @SerializedName("is_portal_locked")
    @Expose
    var isPortalLocked: Int? = null

    @SerializedName("portal_locked_by")
    @Expose
    var portalLockedBy: String? = null

    @SerializedName("portal_unlocked_by")
    @Expose
    var portalUnlockedBy: String? = null

    @SerializedName("is_mail_confirmed")
    @Expose
    var isMailConfirmed: Int? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null

    @SerializedName("is_phone_confirmed")
    @Expose
    var isPhoneConfirmed: Int? = null

    @SerializedName("is_two_factor_enabled")
    @Expose
    var isTwoFactorEnabled: Int? = null

    @SerializedName("theme")
    @Expose
    var theme: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("created_by")
    @Expose
    var createdBy: String? = null

    @SerializedName("updated_by")
    @Expose
    var updatedBy: Int? = null

    @SerializedName("image_version")
    @Expose
    var imageVersion: String? = null

}