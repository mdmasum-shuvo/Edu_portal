package com.masum.edu_portal.myviewmodel

import androidx.lifecycle.*
import com.google.gson.Gson
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.di.SessionManager
import com.masum.edu_portal.feature.myclass.data.ClassListResponse
import com.masum.edu_portal.networks.ApiService
import com.masum.edu_portal.networks.HTTP_PARAM
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import javax.inject.Inject

class ClassViewModel  @Inject constructor(
    val apiService: ApiService,
    val sessionManager: SessionManager
) : ViewModel(){


    val classMateList: MediatorLiveData<DataResource<ClassListResponse>> =
        MediatorLiveData<DataResource<ClassListResponse>>()

    var currentPage = 0
    private val gson = Gson()

    fun getUpComingClassData() {
        currentPage++

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.ORGANISATION_ID, "1")
        builder.addFormDataPart(HTTP_PARAM.STUDENT_ID, "1")

        val source: LiveData<DataResource<ClassListResponse>> =
            LiveDataReactiveStreams.fromPublisher(
                apiService.upComingClassList(
                    builder.build(),
                    "Bearer " + sessionManager.getAuthUser().value!!.data!!.accessToken
                )
                    .onErrorReturn {
                        val user = ClassListResponse()
                        user.data=null
                        user
                    }
                    .map(Function<ClassListResponse, DataResource<ClassListResponse>> { data ->
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