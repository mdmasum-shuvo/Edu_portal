package com.masum.edu_portal.di

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.masum.edu_portal.feature.auth.datamodel.AuthResource
import com.masum.edu_portal.feature.auth.datamodel.LoginResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(){
    private val cechedUser: MediatorLiveData<AuthResource<LoginResponse>> =
        MediatorLiveData<AuthResource<LoginResponse>>()


    fun authenticationUser(source: LiveData<AuthResource<LoginResponse>>){
        if (cechedUser != null) {
           cechedUser.value= AuthResource.loading()
            cechedUser.addSource(
                source,
                object : Observer<AuthResource<LoginResponse>> {
                    override fun onChanged(userAuthResource: AuthResource<LoginResponse>) {
                        cechedUser.value=userAuthResource
                        cechedUser.removeSource(source)
                    }
                })
        } else {
            Log.e(
                "data",
                "Previous session detected ,auth data retrieved from cheched"
            )
        }
    }

    fun logout() {
        Log.e("data", "Logging out")
        cechedUser.value = AuthResource.logout()
    }

    fun getAuthUser(): LiveData<AuthResource<LoginResponse>>{
        return cechedUser
    }
}