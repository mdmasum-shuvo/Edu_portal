package com.masum.edu_portal.myviewmodel

import androidx.lifecycle.*
import com.google.gson.Gson
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.di.SessionManager
import com.masum.edu_portal.feature.exam.data.exam.ExamListResponse
import com.masum.edu_portal.feature.exam.data.question.QuestionResponse
import com.masum.edu_portal.networks.ApiService
import com.masum.edu_portal.networks.HTTP_PARAM
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import javax.inject.Inject

class ExamViewModel @Inject constructor(
    val apiService: ApiService,
    val sessionManager: SessionManager
) : ViewModel() {


    val examList: MediatorLiveData<DataResource<ExamListResponse>> = MediatorLiveData()

    val questionBankList: MediatorLiveData<DataResource<QuestionResponse>> = MediatorLiveData()

    val examQuestionList: MediatorLiveData<DataResource<QuestionResponse>> = MediatorLiveData()

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
                        val user =
                            ExamListResponse()
                        user.data = null
                        user
                    }
                    .map(Function<ExamListResponse, DataResource<ExamListResponse>> { data ->
                        if (data.data == null) {
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


    fun getQuestionListData() {
        currentPage++
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.ORGANISATION_ID, "1")
        builder.addFormDataPart(HTTP_PARAM.SUBJECT_ID, "1")
        builder.addFormDataPart(HTTP_PARAM.CHAPTER_ID, "3")
        builder.addFormDataPart(HTTP_PARAM.CLASS_ID, "9")
        builder.addFormDataPart(HTTP_PARAM.TOPIC_ID, "8")

        val source: LiveData<DataResource<QuestionResponse>> =
            LiveDataReactiveStreams.fromPublisher(
                apiService.questionBank(
                    builder.build(),
                    "Bearer " + sessionManager.getAuthUser().value!!.data!!.accessToken
                )
                    .onErrorReturn {
                        val user =
                            QuestionResponse()
                        user.data = null
                        user
                    }
                    .map(Function<QuestionResponse, DataResource<QuestionResponse>> { data ->
                        if (data.data == null) {
                            DataResource.error("Something went wrong")
                        } else {
                            DataResource.success(data)
                        }
                    })
                    .subscribeOn(Schedulers.io())
            )

        questionBankList.addSource(
            source, Observer { data ->
                questionBankList.value = data
            }
        )
    }

    fun getExamQuestionListData() {
        currentPage++
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.ORGANISATION_ID, "1")
        builder.addFormDataPart(HTTP_PARAM.EXAM_ID, "1")


        val source: LiveData<DataResource<QuestionResponse>> =
            LiveDataReactiveStreams.fromPublisher(
                apiService.examQuestion(
                    builder.build(),
                    "Bearer " + sessionManager.getAuthUser().value!!.data!!.accessToken
                )
                    .onErrorReturn {
                        val user =
                            QuestionResponse()
                        user.data = null
                        user
                    }
                    .map(Function<QuestionResponse, DataResource<QuestionResponse>> { data ->
                        if (data.data == null) {
                            DataResource.error("Something went wrong")
                        } else {
                            DataResource.success(data)
                        }
                    })
                    .subscribeOn(Schedulers.io())
            )

        examQuestionList.addSource(
            source, Observer { data ->
                examQuestionList.value = data
            }
        )
    }
}