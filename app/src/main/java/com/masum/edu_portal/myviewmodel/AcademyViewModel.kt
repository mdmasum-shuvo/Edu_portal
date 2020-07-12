package com.masum.edu_portal.myviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.di.SessionManager
import com.masum.edu_portal.feature.member.data.official.OfficialResponse
import com.masum.edu_portal.networks.ApiService
import com.masum.edu_portal.networks.HTTP_PARAM

import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import javax.inject.Inject
class AcademyViewModel @Inject constructor(
    val apiService: ApiService,
    val sessionManager: SessionManager
) : ViewModel() {
    val officialLiveDataList: MediatorLiveData<DataResource<OfficialResponse>> =
        MediatorLiveData()
    val teacherLiveDataList: MediatorLiveData<DataResource<OfficialResponse>> =
        MediatorLiveData()



    fun getOfficialData() {

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.ORGANISATION_ID, "1")
        officialLiveDataList.value= DataResource.loading()
        val source: LiveData<DataResource<OfficialResponse>> =
            LiveDataReactiveStreams.fromPublisher(
                apiService.officialList(
                    builder.build(),
                    "Bearer " + sessionManager.getAuthUser().value!!.data!!.accessToken
                )
                    .onErrorReturn {
                        val user = OfficialResponse()
                        user.data = null
                        user
                    }
                    .map(Function<OfficialResponse, DataResource<OfficialResponse>> { data ->
                        if (data.data == null) {
                            DataResource.error("Something went wrong")
                        } else {
                            DataResource.success(data)
                        }
                    })
                    .subscribeOn(Schedulers.io())
            )

        officialLiveDataList.addSource(
            source, Observer { data ->
                officialLiveDataList.value = data
            }
        )
    }


    fun getTeacherData() {

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.ORGANISATION_ID, "1")
        teacherLiveDataList.value= DataResource.loading()
        val source: LiveData<DataResource<OfficialResponse>> =
            LiveDataReactiveStreams.fromPublisher(
                apiService.teacherlList(
                    builder.build(),
                    "Bearer " + sessionManager.getAuthUser().value!!.data!!.accessToken
                )
                    .onErrorReturn {
                        val user = OfficialResponse()
                        user.data = null
                        user
                    }
                    .map(Function<OfficialResponse, DataResource<OfficialResponse>> { data ->
                        if (data.data == null) {
                            DataResource.error("Something went wrong")
                        } else {
                            DataResource.success(data)
                        }
                    })
                    .subscribeOn(Schedulers.io())
            )

        teacherLiveDataList.addSource(
            source, Observer { data ->
                teacherLiveDataList.value = data
            }
        )
    }



}