package com.masum.edu_portal.feature.homework.data.all_homework

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("homework_id")
    @Expose
    var homeworkId: Int? = null

    @SerializedName("student_id")
    @Expose
    var studentId: Int? = null

    @SerializedName("text_data")
    @Expose
    var textData: String? = null

    @SerializedName("file_name")
    @Expose
    var fileName: String? = null

    @SerializedName("got_marks")
    @Expose
    var gotMarks: String? = null

    @SerializedName("comments")
    @Expose
    var comments: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("subject_for_group_id")
    @Expose
    var subjectForGroupId: Int? = null

    @SerializedName("homework_title")
    @Expose
    var homeworkTitle: String? = null

    @SerializedName("homework_description")
    @Expose
    var homeworkDescription: String? = null

    @SerializedName("homework_date")
    @Expose
    var homeworkDate: String? = null

    @SerializedName("attached_filename")
    @Expose
    var attachedFilename: String? = null

    @SerializedName("is_assignment")
    @Expose
    var isAssignment: Int? = null

    @SerializedName("assignment_deadline")
    @Expose
    var assignmentDeadline: String? = null

    @SerializedName("sms_sent_count")
    @Expose
    var smsSentCount: String? = null

    @SerializedName("marks")
    @Expose
    var marks: String? = null

    @SerializedName("created_by")
    @Expose
    var createdBy: Int? = null

    @SerializedName("updated_by")
    @Expose
    var updatedBy: String? = null

    @SerializedName("subject_paper_id")
    @Expose
    var subjectPaperId: Int? = null

    @SerializedName("group_id")
    @Expose
    var groupId: Int? = null

    @SerializedName("is_choiseable_subject")
    @Expose
    var isChoiseableSubject: Int? = null

    @SerializedName("subject_id")
    @Expose
    var subjectId: Int? = null

    @SerializedName("paper_name")
    @Expose
    var paperName: String? = null

    @SerializedName("short_name")
    @Expose
    var shortName: String? = null

    @SerializedName("picture")
    @Expose
    var picture: String? = null

    @SerializedName("group_name")
    @Expose
    var groupName: String? = null

    @SerializedName("class_id")
    @Expose
    var classId: Int? = null

    @SerializedName("is_active")
    @Expose
    var isActive: Int? = null

    @SerializedName("deactivated_by")
    @Expose
    var deactivatedBy: String? = null

}