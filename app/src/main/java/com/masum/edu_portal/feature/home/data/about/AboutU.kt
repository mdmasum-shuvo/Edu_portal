package com.masum.edu_portal.feature.home.data.about

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AboutU :Serializable{
    @SerializedName("membership_id")
    @Expose
    var membershipId: String? = null

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

    @SerializedName("committee_status")
    @Expose
    var committeeStatus: Int? = null

    @SerializedName("committee_position")
    @Expose
    var committeePosition: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

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

    @SerializedName("about_us_id")
    @Expose
    var aboutUsId: Int? = null

    @SerializedName("message_type")
    @Expose
    var messageType: String? = null

    @SerializedName("about_us_title")
    @Expose
    var aboutUsTitle: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

}