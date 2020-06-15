package com.masum.edu_portal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.masum.edu_portal.di.SessionManager
import com.masum.edu_portal.feature.auth.datamodel.AuthResource
import com.masum.edu_portal.feature.auth.datamodel.LoginResponse
import com.masum.edu_portal.networks.AuthApi
import com.masum.edu_portal.networks.HTTP_PARAM
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import javax.inject.Inject

class AuthViewModel @Inject constructor(val authApi: AuthApi,val sessionManager: SessionManager) :ViewModel() {


    fun authentication(userName: String,password: String){
        sessionManager.authenticationUser(queryLogin(userName,password))
    }

    fun queryLogin(userName:String,password:String): LiveData<AuthResource<LoginResponse>> {
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.USER_NAME, userName)
        builder.addFormDataPart(HTTP_PARAM.PASSWORD, password)
        return LiveDataReactiveStreams.fromPublisher {
            authApi.authentication(builder.build())
                .onErrorReturn(object : Function<Throwable, LoginResponse> {
                    override fun apply(t: Throwable): LoginResponse {
                        val user = LoginResponse()
                        user.userInfo!!.userId=-1
                        return user
                    }

                })
                .map{user ->
                    if (user.userInfo!!.userId == -1) {
                        AuthResource.error("could not authenticate", null)
                    } else AuthResource.authenticated(user)
                }
                .subscribeOn(Schedulers.io())
        }
    }


   fun observeLogin():LiveData<AuthResource<LoginResponse>>{
       return sessionManager.getAuthUser()
   }

}

