package com.masum.edu_portal.myviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.masum.edu_portal.di.SessionManager
import com.masum.edu_portal.feature.auth.datamodel.AuthResource
import com.masum.edu_portal.feature.auth.datamodel.LoginResponse
import com.masum.edu_portal.feature.auth.datamodel.UserInfo
import com.masum.edu_portal.networks.ApiService
import com.masum.edu_portal.networks.HTTP_PARAM
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    val authApi: ApiService,
    val sessionManager: SessionManager
) : ViewModel() {


    fun authentication(userName: String, password: String,orgId:Int) {
        sessionManager.authenticationUser(queryLogin(userName, password,orgId))
    }


    private fun queryLogin(userName: String, password: String,orgId:Int): LiveData<AuthResource<LoginResponse>> {
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.USER_NAME, userName)
        builder.addFormDataPart(HTTP_PARAM.PASSWORD, password)
        builder.addFormDataPart(HTTP_PARAM.ORGANISATION_ID, orgId.toString())
        return LiveDataReactiveStreams.fromPublisher(
            authApi.authentication(builder.build())
                .onErrorReturn(object : Function<Throwable, LoginResponse> {

                    override fun apply(t: Throwable): LoginResponse {
                        val user = LoginResponse()
                        val userInfo=UserInfo()
                        userInfo.userId=-1
                        user.userInfo=userInfo
                        return user                    }
                })
                .map(Function<LoginResponse, AuthResource<LoginResponse>> { user ->
                    if (user.userInfo!!.userId == -1) {
                        AuthResource.error("could not authenticate")
                    } else AuthResource.authenticated(user)
                })
                .subscribeOn(Schedulers.io())
            )


    }
    fun observeLogin(): LiveData<AuthResource<LoginResponse>> {
        return sessionManager.getAuthUser()
    }

}