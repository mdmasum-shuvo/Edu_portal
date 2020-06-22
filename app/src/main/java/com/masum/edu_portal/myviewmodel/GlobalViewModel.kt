package com.masum.edu_portal.myviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.globaldata.organization.OrgListResponse
import com.masum.edu_portal.networks.ApiService
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalViewModel @Inject constructor(var apiService: ApiService) :ViewModel(){

    val organization:MediatorLiveData<DataResource<OrgListResponse>> =  MediatorLiveData()

    fun getOrganizationList(){
       var source: LiveData<DataResource<OrgListResponse>> =LiveDataReactiveStreams.fromPublisher(
           apiService.organizationList().onErrorReturn(object :
            Function<Throwable, OrgListResponse> {
               override fun apply(t: Throwable): OrgListResponse {
                   val user = OrgListResponse()
                   return user
               }
           })
               .map(Function<OrgListResponse, DataResource<OrgListResponse>> { data ->
                   if (data == null) {
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

}