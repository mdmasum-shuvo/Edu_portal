package com.masum.edu_portal.networks

import com.google.gson.JsonObject
import com.masum.edu_portal.feature.auth.datamodel.LoginResponse
import com.masum.edu_portal.feature.home.data.class_mate.ClassMateResponse
import io.reactivex.Flowable
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
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
        @Body requestBody: RequestBody,@Header(HTTP_PARAM.AUTHORIZATION) token: String?
    ): Flowable<ClassMateResponse>



}