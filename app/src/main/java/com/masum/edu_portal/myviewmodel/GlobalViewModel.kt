package com.masum.edu_portal.myviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.di.SessionManager
import com.masum.edu_portal.globaldata.organization.OrgListResponse
import com.masum.edu_portal.globaldata.subject.SubjectListResponse
import com.masum.edu_portal.networks.ApiService
import com.masum.edu_portal.networks.HTTP_PARAM
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalViewModel @Inject constructor(var apiService: ApiService,var sessionManager: SessionManager) :ViewModel(){

    val organization:MediatorLiveData<DataResource<OrgListResponse>> =  MediatorLiveData()
    val subjectLiveData:MediatorLiveData<DataResource<SubjectListResponse>> =  MediatorLiveData()

    fun getOrganizationList(){
       var source: LiveData<DataResource<OrgListResponse>> =LiveDataReactiveStreams.fromPublisher(
           apiService.organizationList().onErrorReturn(object :
            Function<Throwable, OrgListResponse> {
               override fun apply(t: Throwable): OrgListResponse {
                   val user = OrgListResponse()
                   user.data=null
                   return user
               }
           })
               .map(Function<OrgListResponse, DataResource<OrgListResponse>> { data ->
                   if (data.data == null) {
                       DataResource.error("Something went wrong")
                   } else DataResource.success(data)
               })
               .subscribeOn(Schedulers.io())
       )
        organization.addSource(source, Observer { data->
            organization.value=data
            organization.removeSource(source)
        })

    }



    fun getSubjectList(){
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart(HTTP_PARAM.ORGANISATION_ID, "1")

       var source: LiveData<DataResource<SubjectListResponse>> =LiveDataReactiveStreams.fromPublisher(
           apiService.subjectList(builder.build(),sessionManager.getAuthUser().value!!.data!!.accessToken).onErrorReturn(object :
            Function<Throwable, SubjectListResponse> {
               override fun apply(t: Throwable): SubjectListResponse {
                   val user = SubjectListResponse()
                   user.data=null
                   return user
               }
           })
               .map(Function<SubjectListResponse, DataResource<SubjectListResponse>> { data ->
                   if (data.data == null) {
                       DataResource.error("Something went wrong")
                   } else DataResource.success(data)
               })
               .subscribeOn(Schedulers.io())
       )
        subjectLiveData.addSource(source, Observer { data->
            subjectLiveData.value=data
            subjectLiveData.removeSource(source)
        })

    }

}