package com.masum.edu_portal.myviewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.di.SessionManager
import com.masum.edu_portal.feature.homework.data.all_homework.HomeWorkResponse
import com.masum.edu_portal.feature.study.data.all_study.AllStudyResponse
import com.masum.edu_portal.networks.ApiService
import com.masum.edu_portal.networks.HTTP_PARAM
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import javax.inject.Inject
class HomeWorkViewModel @Inject constructor(
    val apiService: ApiService,
    val sessionManager: SessionManager
) : ViewModel() {
    val allHomeworkList: MediatorLiveData<DataResource<HomeWorkResponse>> =
        MediatorLiveData()
    val newHomeworkList: MediatorLiveData<DataResource<HomeWorkResponse>> =
        MediatorLiveData()
    val lastClassStudyDataList: MediatorLiveData<DataResource<AllStudyResponse>> =
        MediatorLiveData()


    fun getAllHomework() {

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.ORGANISATION_ID, "1")
        builder.addFormDataPart(HTTP_PARAM.STUDENT_ID, "1")
        allHomeworkList.value= DataResource.loading()
        val source: LiveData<DataResource<HomeWorkResponse>> =
            LiveDataReactiveStreams.fromPublisher(
                apiService.allHomework(
                    builder.build(),
                    "Bearer " + sessionManager.getAuthUser().value!!.data!!.accessToken
                )
                    .onErrorReturn {
                        val user = HomeWorkResponse()
                        user.data = null
                        user
                    }
                    .map(Function<HomeWorkResponse, DataResource<HomeWorkResponse>> { data ->
                        if (data.data == null) {
                            DataResource.error("Something went wrong")
                        } else {
                            DataResource.success(data)
                        }
                    })
                    .subscribeOn(Schedulers.io())
            )

        allHomeworkList.addSource(
            source, Observer { data ->
                allHomeworkList.value = data
            }
        )
    }

    fun getNewHomework() {

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.ORGANISATION_ID, "1")
        builder.addFormDataPart(HTTP_PARAM.STUDENT_ID, "1")

        val source: LiveData<DataResource<HomeWorkResponse>> =
            LiveDataReactiveStreams.fromPublisher(
                apiService.newHomework(
                    builder.build(),
                    "Bearer " + sessionManager.getAuthUser().value!!.data!!.accessToken
                )
                    .onErrorReturn {
                        val user = HomeWorkResponse()
                        user.data = null
                        user
                    }
                    .map(Function<HomeWorkResponse, DataResource<HomeWorkResponse>> { data ->
                        if (data.data == null) {
                            DataResource.error("Something went wrong")
                        } else {
                            DataResource.success(data)
                        }
                    })
                    .subscribeOn(Schedulers.io())
            )

        newHomeworkList.addSource(
            source, Observer { data ->
                newHomeworkList.value = data
            }
        )
    }

    fun postHomeworkAttachment() {

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