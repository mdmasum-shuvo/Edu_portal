package com.masum.edu_portal.feature.study.data.all_study

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Datum :Serializable{
    @SerializedName("lecture_id")
    @Expose
    var lectureId: Int? = null

    @SerializedName("lecture_title")
    @Expose
    var lectureTitle: String? = null

    @SerializedName("upload_date_time")
    @Expose
    var uploadDateTime: String? = null

    @SerializedName("subject_id")
    @Expose
    var subjectId: Int? = null

    @SerializedName("teacher_id")
    @Expose
    var teacherId: String? = null

    @SerializedName("weekly_routine_id")
    @Expose
    var weeklyRoutineId: String? = null

    @SerializedName("video_url")
    @Expose
    var videoUrl: String? = null

    @SerializedName("attachment")
    @Expose
    var attachment: String? = null

    @SerializedName("lecture_type")
    @Expose
    var lectureType: Int? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("created_by")
    @Expose
    var createdBy: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("updated_by")
    @Expose
    var updatedBy: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("class_type")
    @Expose
    var classType: String? = null

    @SerializedName("section_id")
    @Expose
    var sectionId: String? = null

    @SerializedName("period_id")
    @Expose
    var periodId: String? = null

    @SerializedName("day")
    @Expose
    var day: String? = null

    @SerializedName("zoom_user")
    @Expose
    var zoomUser: String? = null

    @SerializedName("zoom_pass")
    @Expose
    var zoomPass: String? = null

    @SerializedName("day_id")
    @Expose
    var dayId: String? = null

    @SerializedName("db_date")
    @Expose
    var dbDate: String? = null

    @SerializedName("year")
    @Expose
    var year: String? = null

    @SerializedName("month")
    @Expose
    var month: String? = null

    @SerializedName("quarter")
    @Expose
    var quarter: String? = null

    @SerializedName("week")
    @Expose
    var week: String? = null

    @SerializedName("day_name")
    @Expose
    var dayName: String? = null

    @SerializedName("month_name")
    @Expose
    var monthName: String? = null

    @SerializedName("holiday_flag")
    @Expose
    var holidayFlag: String? = null

    @SerializedName("weekend_flag")
    @Expose
    var weekendFlag: String? = null

    @SerializedName("event")
    @Expose
    var event: String? = null

    @SerializedName("subject_paper_id")
    @Expose
    var subjectPaperId: Int? = null

    @SerializedName("paper_name")
    @Expose
    var paperName: String? = null

    @SerializedName("short_name")
    @Expose
    var shortName: String? = null

    @SerializedName("picture")
    @Expose
    var picture: String? = null

    @SerializedName("member_id")
    @Expose
    var memberId: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

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
    var isCvSaved: String? = null

    @SerializedName("is_resigned")
    @Expose
    var isResigned: String? = null

    @SerializedName("is_active")
    @Expose
    var isActive: String? = null

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
    var isTeacher: String? = null

    @SerializedName("period_name")
    @Expose
    var periodName: String? = null

    @SerializedName("start_at")
    @Expose
    var startAt: String? = null

    @SerializedName("ends_at")
    @Expose
    var endsAt: String? = null

}