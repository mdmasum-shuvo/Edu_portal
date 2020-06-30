package com.masum.edu_portal.networks

import com.masum.edu_portal.feature.auth.datamodel.LoginResponse
import com.masum.edu_portal.feature.exam.data.ExamListResponse
import com.masum.edu_portal.feature.home.data.class_mate.ClassMateResponse
import com.masum.edu_portal.feature.member.data.profile.attendance.AttendanceResponse
import com.masum.edu_portal.feature.myclass.data.ClassListResponse
import com.masum.edu_portal.globaldata.organization.OrgListResponse
import io.reactivex.Flowable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


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
    fun upComingClassList(@Body requestBody: RequestBody,@Header(HTTP_PARAM.AUTHORIZATION) token: String?) :Flowable<ClassListResponse>


    //exam
    @POST(HTTP_PARAM.EXAM_LIST)
    fun upComingExamList(@Body requestBody: RequestBody,@Header(HTTP_PARAM.AUTHORIZATION) token: String?) :Flowable<ExamListResponse>

    //global data
    @GET(HTTP_PARAM.ORGANIZATION_LIST)
    fun organizationList(): Flowable<OrgListResponse>


    //class attendance history
    @POST(HTTP_PARAM.ATTENDANCE_HISTORY)
    fun attendanceHistory(@Body requestBody: RequestBody,@Header(HTTP_PARAM.AUTHORIZATION) token: String?) :Flowable<AttendanceResponse>



}