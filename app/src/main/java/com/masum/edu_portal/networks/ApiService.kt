package com.masum.edu_portal.networks

import com.masum.edu_portal.feature.auth.datamodel.LoginResponse
import com.masum.edu_portal.feature.exam.data.exam.ExamListResponse
import com.masum.edu_portal.feature.exam.data.question.QuestionResponse
import com.masum.edu_portal.feature.home.data.class_mate.ClassMateResponse
import com.masum.edu_portal.feature.member.data.profile.attendance.AttendanceResponse
import com.masum.edu_portal.feature.myclass.data.ClassListResponse
import com.masum.edu_portal.feature.study.data.all_study.AllStudyResponse
import com.masum.edu_portal.globaldata.organization.OrgListResponse
import com.masum.edu_portal.globaldata.subject.SubjectListResponse
import io.reactivex.Flowable
import okhttp3.RequestBody
import retrofit2.http.*


interface ApiService {

    //auth
    @POST(HTTP_PARAM.LOGIN)
    fun authentication(
        @Body requestBody: RequestBody
    ): Flowable<LoginResponse>

    //class mate list
    @POST(HTTP_PARAM.CLASS_MATE_LIST)
    fun classMateList(
        @Body requestBody: RequestBody, @Header(HTTP_PARAM.AUTHORIZATION) token: String?
    ): Flowable<ClassMateResponse>

    //class
    @POST(HTTP_PARAM.CLASS_LIST)
    fun upComingClassList(
        @Body requestBody: RequestBody,
        @Header(HTTP_PARAM.AUTHORIZATION) token: String?
    ): Flowable<ClassListResponse>


    //exam
    @POST(HTTP_PARAM.EXAM_LIST)
    fun upComingExamList(
        @Body requestBody: RequestBody,
        @Header(HTTP_PARAM.AUTHORIZATION) token: String?
    ): Flowable<ExamListResponse>


    @POST(HTTP_PARAM.QUESTION_BANK)
    fun questionBank(
        @Body requestBody: RequestBody,
        @Header(HTTP_PARAM.AUTHORIZATION) token: String?
    ): Flowable<QuestionResponse>

    @POST(HTTP_PARAM.EXAM_QUESTION)
    fun examQuestion(
        @Body requestBody: RequestBody,
        @Header(HTTP_PARAM.AUTHORIZATION) token: String?
    ): Flowable<QuestionResponse>


    //global data
    @GET(HTTP_PARAM.ORGANIZATION_LIST)
    fun organizationList(): Flowable<OrgListResponse>


    @GET(HTTP_PARAM.SUBJECT_LIST)
    fun subjectList(
        @Body requestBody: RequestBody,
        @Header(HTTP_PARAM.AUTHORIZATION) token: String?
    ): Flowable<SubjectListResponse>


    //class attendance history
    @POST(HTTP_PARAM.ATTENDANCE_HISTORY)
    fun attendanceHistory(
        @Body requestBody: RequestBody,
        @Header(HTTP_PARAM.AUTHORIZATION) token: String?
    ): Flowable<AttendanceResponse>


    //class lecture

    @POST(HTTP_PARAM.ALL_STUDY_LIST)
    fun allLecture(
        @Body requestBody: RequestBody,
        @Header(HTTP_PARAM.AUTHORIZATION) token: String?
    ): Flowable<AllStudyResponse>

    @POST(HTTP_PARAM.ALL_STUDY_VIDEO_LIST)
    fun allVideoLecture(
        @Body requestBody: RequestBody,
        @Header(HTTP_PARAM.AUTHORIZATION) token: String?
    ): Flowable<AllStudyResponse>

    @POST(HTTP_PARAM.LAST_CLASS_LECTURE)
    fun lastClassLecture(
        @Body requestBody: RequestBody,
        @Header(HTTP_PARAM.AUTHORIZATION) token: String?
    ): Flowable<AllStudyResponse>


}