package com.masum.edu_portal.feature.exam.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Datum :Serializable{
    @SerializedName("exam_students_subjects_id")
    @Expose
    var examStudentsSubjectsId: Int? = null

    @SerializedName("exam_id")
    @Expose
    var examId: Int? = null

    @SerializedName("student_id")
    @Expose
    var studentId: Int? = null

    @SerializedName("subject_for_group_id")
    @Expose
    var subjectForGroupId: Int? = null

    @SerializedName("is_choseable")
    @Expose
    var isChoseable: Int? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("exam_name")
    @Expose
    var examName: String? = null

    @SerializedName("starts")
    @Expose
    var starts: String? = null

    @SerializedName("ends")
    @Expose
    var ends: String? = null

    @SerializedName("exam_category_id")
    @Expose
    var examCategoryId: Int? = null

    @SerializedName("show_on_portal")
    @Expose
    var showOnPortal: Int? = null

    @SerializedName("main_exam_mark_percentage")
    @Expose
    var mainExamMarkPercentage: String? = null

    @SerializedName("attendance_percentage")
    @Expose
    var attendancePercentage: String? = null

    @SerializedName("attendance_starts")
    @Expose
    var attendanceStarts: String? = null

    @SerializedName("attendance_ends")
    @Expose
    var attendanceEnds: String? = null

    @SerializedName("sms_sent_count")
    @Expose
    var smsSentCount: String? = null

    @SerializedName("published_count")
    @Expose
    var publishedCount: String? = null

    @SerializedName("show_as_schedule")
    @Expose
    var showAsSchedule: Int? = null

    @SerializedName("exam_type")
    @Expose
    var examType: String? = null

    @SerializedName("created_by")
    @Expose
    var createdBy: String? = null

    @SerializedName("updated_by")
    @Expose
    var updatedBy: String? = null

}