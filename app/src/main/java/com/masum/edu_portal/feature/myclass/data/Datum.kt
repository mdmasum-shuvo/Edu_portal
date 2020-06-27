package com.masum.edu_portal.feature.myclass.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("section_id")
    @Expose
    var sectionId: Int? = null

    @SerializedName("period_id")
    @Expose
    var periodId: Int? = null

    @SerializedName("teacher_id")
    @Expose
    var teacherId: Int? = null

    @SerializedName("subject_id")
    @Expose
    var subjectId: Int? = null

    @SerializedName("day")
    @Expose
    var day: Int? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("day_id")
    @Expose
    var dayId: Int? = null

    @SerializedName("db_date")
    @Expose
    var dbDate: String? = null

    @SerializedName("year")
    @Expose
    var year: Int? = null

    @SerializedName("month")
    @Expose
    var month: Int? = null

    @SerializedName("quarter")
    @Expose
    var quarter: Int? = null

    @SerializedName("week")
    @Expose
    var week: Int? = null

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

}