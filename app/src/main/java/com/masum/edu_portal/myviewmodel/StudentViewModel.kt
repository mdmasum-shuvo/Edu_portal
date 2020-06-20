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
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StudentViewModel @Inject constructor(
    val apiService: ApiService,
    val sessionManager: SessionManager
) : ViewModel() {
    val classMateList: MediatorLiveData<DataResource<ClassMateResponse>> =
        MediatorLiveData<DataResource<ClassMateResponse>>()

    val classmateData: MutableLiveData<List<Datum>> = MutableLiveData()
    var currentPage = 0
    private val gson = Gson()

    fun getClassMateData() {
        currentPage++
        var classMate: ClassMatePost = ClassMatePost()

        classMate.orgId = 1
        classMate.classId = 40
        classMate.groupId = 50
        classMate.sectionId = 88
        classMate.currentPage = 1
        val jsonString: String = gson.toJson(classMate)
        val jsonObject = JsonParser().parse(jsonString).asJsonObject
        val source: LiveData<AuthResource<ClassMateResponse>> =
            LiveDataReactiveStreams.fromPublisher {
                apiService.classMateList(jsonObject,sessionManager.getAuthUser().value!!.data!!.accessToken)
                    .onErrorReturn(object : Function<Throwable, ClassMateResponse> {

                        override fun apply(t: Throwable): ClassMateResponse {
                            val user = ClassMateResponse()

                            return user
                        }
                    })
                    .map(Function<ClassMateResponse, DataResource<ClassMateResponse>> { user ->
                        if (user == null) {
                            DataResource.error("could not authenticate")
                        } else DataResource.success(user)
                    })
                    .subscribeOn(Schedulers.io())
            }

        classMateList.addSource(
            source, Observer { data ->
                classmateData.value = data.data!!.data!!.data
            }
        )
    }
}