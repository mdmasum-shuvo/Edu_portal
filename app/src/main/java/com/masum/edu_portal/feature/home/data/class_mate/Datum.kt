package com.masum.edu_portal.feature.home.data.class_mate

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Datum : Serializable {
    @SerializedName("campus_id")
    @Expose
    var campusId: Int? = null

    @SerializedName("session_id")
    @Expose
    var sessionId: Int? = null

    @SerializedName("shift_id")
    @Expose
    var shiftId: Int? = null

    @SerializedName("medium_id")
    @Expose
    var mediumId: Int? = null

    @SerializedName("class_id")
    @Expose
    var classId: Int? = null

    @SerializedName("group_id")
    @Expose
    var groupId: Int? = null

    @SerializedName("section_id")
    @Expose
    var sectionId: Int? = null

    @SerializedName("campus_name")
    @Expose
    var campusName: String? = null

    @SerializedName("session_name")
    @Expose
    var sessionName: String? = null

    @SerializedName("shift_name")
    @Expose
    var shiftName: String? = null

    @SerializedName("medium_name")
    @Expose
    var mediumName: String? = null

    @SerializedName("class_name")
    @Expose
    var className: String? = null

    @SerializedName("group_name")
    @Expose
    var groupName: String? = null

    @SerializedName("section_name")
    @Expose
    var sectionName: String? = null

    @SerializedName("student_id")
    @Expose
    var studentId: Int? = null

    @SerializedName("student_name")
    @Expose
    var studentName: String? = null

    @SerializedName("student_gender")
    @Expose
    var studentGender: String? = null

    @SerializedName("student_nationality")
    @Expose
    var studentNationality: String? = null

    @SerializedName("student_religion")
    @Expose
    var studentReligion: String? = null

    @SerializedName("student_dob")
    @Expose
    var studentDob: Any? = null

    @SerializedName("student_contact_no")
    @Expose
    var studentContactNo: String? = null

    @SerializedName("student_email")
    @Expose
    var studentEmail: String? = null

    @SerializedName("father_name")
    @Expose
    var fatherName: String? = null

    @SerializedName("father_occupation")
    @Expose
    var fatherOccupation: String? = null

    @SerializedName("father_income")
    @Expose
    var fatherIncome: String? = null

    @SerializedName("father_contact_no")
    @Expose
    var fatherContactNo: String? = null

    @SerializedName("mother_name")
    @Expose
    var motherName: String? = null

    @SerializedName("mother_email")
    @Expose
    var motherEmail: String? = null

    @SerializedName("mother_occupation")
    @Expose
    var motherOccupation: String? = null

    @SerializedName("mother_income")
    @Expose
    var motherIncome: String? = null

    @SerializedName("mother_contact_no")
    @Expose
    var motherContactNo: String? = null

    @SerializedName("local_guardian")
    @Expose
    var localGuardian: String? = null

    @SerializedName("local_guardian_contact_no")
    @Expose
    var localGuardianContactNo: String? = null

    @SerializedName("relation_with_local_guardian")
    @Expose
    var relationWithLocalGuardian: String? = null

    @SerializedName("present_address")
    @Expose
    var presentAddress: String? = null

    @SerializedName("permanent_address")
    @Expose
    var permanentAddress: String? = null

    @SerializedName("provided_device_info")
    @Expose
    var providedDeviceInfo: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

    @SerializedName("unique_roll_no")
    @Expose
    var uniqueRollNo: String? = null

    @SerializedName("class_roll_no")
    @Expose
    var classRollNo: String? = null

    @SerializedName("rfid_number")
    @Expose
    var rfidNumber: String? = null

    @SerializedName("member_id")
    @Expose
    var memberId: String? = null

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

    @SerializedName("dob")
    @Expose
    var dob: String? = null

    @SerializedName("education")
    @Expose
    var education: String? = null

    @SerializedName("designation")
    @Expose
    var designation: String? = null

}