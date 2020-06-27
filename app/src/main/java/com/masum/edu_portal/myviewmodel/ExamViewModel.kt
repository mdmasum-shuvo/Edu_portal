package com.masum.edu_portal.myviewmodel

import androidx.lifecycle.*
import com.google.gson.Gson
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.di.SessionManager
import com.masum.edu_portal.feature.exam.data.ExamListResponse
import com.masum.edu_portal.networks.ApiService
import com.masum.edu_portal.networks.HTTP_PARAM
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import javax.inject.Inject

class ExamViewModel @Inject constructor(
    val apiService: ApiService,
    val sessionManager: SessionManager
) :ViewModel(){


    val examList: MediatorLiveData<DataResource<ExamListResponse>> =
        MediatorLiveData<DataResource<ExamListResponse>>()

    var currentPage = 0
    private val gson = Gson()

    fun getUpComingExamData() {
        currentPage++

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.ORGANISATION_ID, "1")
        builder.addFormDataPart(HTTP_PARAM.SUBJECT_ID, "1")
        builder.addFormDataPart(HTTP_PARAM.STUDENT_ID, "1")

        val source: LiveData<DataResource<ExamListResponse>> =
            LiveDataReactiveStreams.fromPublisher(
                apiService.upComingExamList(
                    builder.build(),
                    "Bearer " + sessionManager.getAuthUser().value!!.data!!.accessToken
                )
                    .onErrorReturn {
                        val user = ExamListResponse()
                        user.data=null
                        user
                    }
                    .map(Function<ExamListResponse, DataResource<ExamListResponse>> { data ->
                        if (data.data==null) {
                            DataResource.error("Something went wrong")
                        } else {
                            DataResource.success(data)
                        }
                    })
                    .subscribeOn(Schedulers.io())
            )

        examList.addSource(
            source, Observer { data ->
                examList.value = data
            }
        )
    }
}