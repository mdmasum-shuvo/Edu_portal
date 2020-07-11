package com.masum.edu_portal.feature.exam.data.question

import android.util.Log
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {
    @SerializedName("question_id")
    @Expose
    var questionId: Int? = null

    @SerializedName("question_bank_id")
    @Expose
    var questionBankId: Int? = null

    @SerializedName("number_of_question")
    @Expose
    var numberOfQuestion: Int? = null

    @SerializedName("bank_title")
    @Expose
    var bankTitle: String? = null

    @SerializedName("duration")
    @Expose
    var duration: Long? = null

    @SerializedName("question_title")
    @Expose
    var questionTitle: String? = null

    @SerializedName("class_id")
    @Expose
    var classId: Int? = null

    @SerializedName("subject_id")
    @Expose
    var subjectId: Int? = null

    @SerializedName("chapter_id")
    @Expose
    var chapterId: Int? = null

    @SerializedName("topic_id")
    @Expose
    var topicId: Int? = null

    @SerializedName("chapter_serial_no")
    @Expose
    var chapterSerialNo: String? = null

    @SerializedName("chapter_title")
    @Expose
    var chapterTitle: String? = null

    @SerializedName("topic_serial_no")
    @Expose
    var topicSerialNo: String? = null

    @SerializedName("topic_title")
    @Expose
    var topicTitle: String? = null

    @SerializedName("option_title")
    @Expose
    var optionTitle: String? = null

    @SerializedName("is_answer")
    @Expose
    var isAnswer: String? = null

}