package com.masum.edu_portal.myviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import com.google.gson.Gson
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.di.SessionManager
import com.masum.edu_portal.feature.member.data.profile.attendance.AttendanceResponse
import com.masum.edu_portal.networks.ApiService
import com.masum.edu_portal.networks.HTTP_PARAM
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    val apiService: ApiService,
    val sessionManager: SessionManager
) : ViewModel() {

    val attendanceHistoryData: MediatorLiveData<DataResource<AttendanceResponse>> =
        MediatorLiveData<DataResource<AttendanceResponse>>()

    var currentPage = 0
    private val gson = Gson()

    fun getAttendanceHistory() {
        currentPage++

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.ORGANISATION_ID, "1")
        builder.addFormDataPart(HTTP_PARAM.START_DATE, "2020-06-22")
        builder.addFormDataPart(HTTP_PARAM.END_DATE, "2020-06-28")

        attendanceHistoryData.value = DataResource.loading()
        val source: LiveData<DataResource<AttendanceResponse>> =
            LiveDataReactiveStreams.fromPublisher(
                apiService.attendanceHistory(
                    builder.build(),
                    "Bearer " + sessionManager.getAuthUser().value!!.data!!.accessToken
                )
                    .onErrorReturn {
                        val user = AttendanceResponse()
                        user.data = null
                        user
                    }
                    .map(Function<AttendanceResponse, DataResource<AttendanceResponse>> { data ->
                        if (data.data == null) {
                            DataResource.error("Something went wrong")
                        } else {
                            DataResource.success(data)
                        }
                    })
                    .subscribeOn(Schedulers.io())
            )

        attendanceHistoryData.addSource(
            source, Observer { data ->
                attendanceHistoryData.value = data
            }
        )
    }
}