package com.masum.edu_portal.feature.member.data.official

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {
    @SerializedName("member_id")
    @Expose
    var memberId: Int? = null

    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

    @SerializedName("unique_card_id")
    @Expose
    var uniqueCardId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("contact_no")
    @Expose
    var contactNo: String? = null

    @SerializedName("father_name")
    @Expose
    var fatherName: String? = null

    @SerializedName("mother_name")
    @Expose
    var motherName: String? = null

    @SerializedName("dob")
    @Expose
    var dob: String? = null

    @SerializedName("present_address")
    @Expose
    var presentAddress: String? = null

    @SerializedName("permanent_address")
    @Expose
    var permanentAddress: String? = null

    @SerializedName("education")
    @Expose
    var education: String? = null

    @SerializedName("designation")
    @Expose
    var designation: String? = null

    @SerializedName("appointment_date")
    @Expose
    var appointmentDate: String? = null

    @SerializedName("is_cv_saved")
    @Expose
    var isCvSaved: Int? = null

    @SerializedName("is_resigned")
    @Expose
    var isResigned: Int? = null

    @SerializedName("is_active")
    @Expose
    var isActive: Int? = null

    @SerializedName("inactive_reason")
    @Expose
    var inactiveReason: String? = null

    @SerializedName("resign_date")
    @Expose
    var resignDate: String? = null

    @SerializedName("resign_reason")
    @Expose
    var resignReason: String? = null

    @SerializedName("work_experience")
    @Expose
    var workExperience: String? = null

    @SerializedName("previous_salary")
    @Expose
    var previousSalary: String? = null

    @SerializedName("starting_salary")
    @Expose
    var startingSalary: String? = null

    @SerializedName("current_salary")
    @Expose
    var currentSalary: String? = null

    @SerializedName("other_information")
    @Expose
    var otherInformation: String? = null

    @SerializedName("initial_portal_pass")
    @Expose
    var initialPortalPass: String? = null

    @SerializedName("cv_file_extension")
    @Expose
    var cvFileExtension: String? = null

    @SerializedName("rfid_number")
    @Expose
    var rfidNumber: String? = null

    @SerializedName("is_teacher")
    @Expose
    var isTeacher: Int? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("created_by")
    @Expose
    var createdBy: Int? = null

    @SerializedName("updated_by")
    @Expose
    var updatedBy: String? = null

}