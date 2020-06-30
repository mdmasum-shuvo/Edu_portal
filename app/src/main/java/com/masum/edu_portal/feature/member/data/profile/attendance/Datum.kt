package com.masum.edu_portal.feature.member.data.profile.attendance

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {
    @SerializedName("attendance_id")
    @Expose
    var attendanceId: Int? = null

    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

    @SerializedName("weekly_routine_id")
    @Expose
    var weeklyRoutineId: Int? = null

    @SerializedName("attendance_date")
    @Expose
    var attendanceDate: String? = null

    @SerializedName("entry_time")
    @Expose
    var entryTime: String? = null

    @SerializedName("exit_time")
    @Expose
    var exitTime: String? = null

    @SerializedName("attendance_status")
    @Expose
    var attendanceStatus: String? = null

    @SerializedName("is_edited")
    @Expose
    var isEdited: String? = null

    @SerializedName("edited_by")
    @Expose
    var editedBy: String? = null

    @SerializedName("editing_reason")
    @Expose
    var editingReason: String? = null

    @SerializedName("old_entry_time")
    @Expose
    var oldEntryTime: String? = null

    @SerializedName("old_exit_time")
    @Expose
    var oldExitTime: String? = null

    @SerializedName("comments")
    @Expose
    var comments: String? = null

    @SerializedName("creadted_at")
    @Expose
    var creadtedAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

}