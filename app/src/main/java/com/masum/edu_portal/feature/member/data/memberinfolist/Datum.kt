/*
 * *
 *  * Created by Md Masum Talukder on 5/16/20 6:39 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/14/20 6:26 PM
 *
 */
package com.masum.edu_portal.feature.member.data.memberinfolist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.masum.edu_portal.BuildConfig
import java.io.Serializable

class Datum : Serializable {
    @SerializedName("member_id")
    @Expose
    var memberId: Int? = null

    @SerializedName("membership_id")
    @Expose
    var membershipId: String? = null
        get() = if (field == null) {
            ""
        } else field

    @SerializedName("full_name_english")
    @Expose
    var fullNameEnglish: String? = null

    @SerializedName("full_name_bangla")
    @Expose
    var fullNameBangla: String? = null

    @SerializedName("current_stage")
    @Expose
    var currentStage: String? = null

    @SerializedName("nid")
    @Expose
    var nid: String? = null

    @SerializedName("mobile")
    @Expose
    var mobile: String? = null

    @SerializedName("telephone_home")
    @Expose
    var telephoneHome: String? = null

    @SerializedName("telephone_office")
    @Expose
    var telephoneOffice: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("blood_group")
    @Expose
    var bloodGroup: String? = null

    @SerializedName("current_address")
    @Expose
    var currentAddress: String? = null

    @SerializedName("spouse_name")
    @Expose
    var spouseName: String? = null

    @SerializedName("spouse_occupation_id")
    @Expose
    var spouseOccupationId: String? = null

    @SerializedName("emergency_contact_no")
    @Expose
    var emergencyContactNo: String? = null

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("committee_status")
    @Expose
    var committeeStatus: Int? = null

    @SerializedName("committee_position")
    @Expose
    var committeePosition: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null
        get() = BuildConfig.IMG_URL + field

    @SerializedName("date_of_birth")
    @Expose
    var dateOfBirth: String? = null

    @SerializedName("district_name_engish")
    @Expose
    var districtNameEngish: String? = null

    @SerializedName("district_name_bangla")
    @Expose
    var districtNameBangla: String? = null

    @SerializedName("designation_name")
    @Expose
    var designationName: String? = null

    @SerializedName("cadre_name")
    @Expose
    var cadreName: String? = null

    @SerializedName("occupation_name")
    @Expose
    var occupationName: String? = null

}