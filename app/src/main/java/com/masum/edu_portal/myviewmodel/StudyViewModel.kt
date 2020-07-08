package com.masum.edu_portal.myviewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.di.SessionManager
import com.masum.edu_portal.feature.study.data.all_study.AllStudyResponse
import com.masum.edu_portal.networks.ApiService
import com.masum.edu_portal.networks.HTTP_PARAM
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import javax.inject.Inject

class StudyViewModel @Inject constructor(
    val apiService: ApiService,
    val sessionManager: SessionManager
) : ViewModel() {
    val allStudyDataList: MediatorLiveData<DataResource<AllStudyResponse>> =
        MediatorLiveData()
    val videoStudyDataList: MediatorLiveData<DataResource<AllStudyResponse>> =
        MediatorLiveData()
    val lastClassStudyDataList: MediatorLiveData<DataResource<AllStudyResponse>> =
        MediatorLiveData()


    fun getAllLecture() {

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.ORGANISATION_ID, "1")
        allStudyDataList.value= DataResource.loading()
        val source: LiveData<DataResource<AllStudyResponse>> =
            LiveDataReactiveStreams.fromPublisher(
                apiService.allLecture(
                    builder.build(),
                    "Bearer " + sessionManager.getAuthUser().value!!.data!!.accessToken
                )
                    .onErrorReturn {
                        val user = AllStudyResponse()
                        user.data = null
                        user
                    }
                    .map(Function<AllStudyResponse, DataResource<AllStudyResponse>> { data ->
                        if (data.data == null) {
                            DataResource.error("Something went wrong")
                        } else {
                            DataResource.success(data)
                        }
                    })
                    .subscribeOn(Schedulers.io())
            )

        allStudyDataList.addSource(
            source, Observer { data ->
                allStudyDataList.value = data
            }
        )
    }

    fun getVideoLecture() {

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.ORGANISATION_ID, "1")

        val source: LiveData<DataResource<AllStudyResponse>> =
            LiveDataReactiveStreams.fromPublisher(
                apiService.allVideoLecture(
                    builder.build(),
                    "Bearer " + sessionManager.getAuthUser().value!!.data!!.accessToken
                )
                    .onErrorReturn {
                        val user = AllStudyResponse()
                        user.data = null
                        user
                    }
                    .map(Function<AllStudyResponse, DataResource<AllStudyResponse>> { data ->
                        if (data.data == null) {
                            DataResource.error("Something went wrong")
                        } else {
                            DataResource.success(data)
                        }
                    })
                    .subscribeOn(Schedulers.io())
            )

        videoStudyDataList.addSource(
            source, Observer { data ->
                videoStudyDataList.value = data
            }
        )
    }

    fun getLastClassLecture() {

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.ORGANISATION_ID, "1")

        val source: LiveData<DataResource<AllStudyResponse>> =
            LiveDataReactiveStreams.fromPublisher(
                apiService.lastClassLecture(
                    builder.build(),
                    "Bearer " + sessionManager.getAuthUser().value!!.data!!.accessToken
                )
                    .onErrorReturn {
                        val user = AllStudyResponse()
                        user.data = null
                        user
                    }
                    .map(Function<AllStudyResponse, DataResource<AllStudyResponse>> { data ->
                        if (data.data == null) {
                            DataResource.error("Something went wrong")
                        } else {
                            DataResource.success(data)
                        }
                    })
                    .subscribeOn(Schedulers.io())
            )

        lastClassStudyDataList.addSource(
            source, Observer { data ->
                lastClassStudyDataList.value = data
            }
        )
    }

}