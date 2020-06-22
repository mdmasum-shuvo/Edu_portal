package com.masum.edu_portal.myviewmodel

import androidx.lifecycle.*
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.di.SessionManager
import com.masum.edu_portal.feature.auth.datamodel.AuthResource
import com.masum.edu_portal.feature.auth.datamodel.LoginResponse
import com.masum.edu_portal.feature.auth.datamodel.UserInfo
import com.masum.edu_portal.feature.home.data.class_mate.ClassMatePost
import com.masum.edu_portal.feature.home.data.class_mate.ClassMateResponse
import com.masum.edu_portal.feature.home.data.class_mate.Datum
import com.masum.edu_portal.networks.ApiService
import com.masum.edu_portal.networks.HTTP_PARAM
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import javax.inject.Inject

class StudentViewModel @Inject constructor(
    val apiService: ApiService,
    val sessionManager: SessionManager
) : ViewModel() {
    val classMateList: MediatorLiveData<DataResource<ClassMateResponse>> =
        MediatorLiveData<DataResource<ClassMateResponse>>()

    var currentPage = 0
    private val gson = Gson()

    fun getClassMateData() {
        currentPage++

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.ORGANISATION_ID, "1")
        builder.addFormDataPart(HTTP_PARAM.CLASS_ID, "40")
        builder.addFormDataPart(HTTP_PARAM.GROUP_ID, "50")
        builder.addFormDataPart(HTTP_PARAM.SECTION_ID, "88")

        val source: LiveData<DataResource<ClassMateResponse>> =
            LiveDataReactiveStreams.fromPublisher(
                apiService.classMateList(
                    builder.build(),
                    "Bearer " + sessionManager.getAuthUser().value!!.data!!.accessToken
                )
                    .onErrorReturn {
                        val user = ClassMateResponse()
                        user.data=null
                        user
                    }
                    .map(Function<ClassMateResponse, DataResource<ClassMateResponse>> { data ->
                        if (data.data==null) {
                            DataResource.error("Something went wrong")
                        } else {
                            DataResource.success(data)
                        }
                    })
                    .subscribeOn(Schedulers.io())
            )

        classMateList.addSource(
            source, Observer { data ->
                classMateList.value = data
            }
        )
    }
}