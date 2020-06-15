package com.masum.edu_portal.networks

import com.masum.edu_portal.feature.auth.datamodel.LoginResponse
import io.reactivex.Flowable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    //auth
    @POST(HTTP_PARAM.LOGIN)
    open fun authentication(
        @Body requestBody: RequestBody
    ): Flowable<LoginResponse>

}