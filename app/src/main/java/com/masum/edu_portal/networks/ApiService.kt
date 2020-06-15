package com.masum.edu_portal.networks

import com.masum.edu_portal.feature.home.data.CommiteeMemberResponse
import com.masum.edu_portal.feature.home.data.NotificaitonSaveResponse
import com.masum.edu_portal.feature.home.data.about.AboutUsDataResponse
import com.masum.edu_portal.feature.home.data.designation.DesignationResponse
import com.masum.edu_portal.feature.home.data.notifications.NotificationResponse
import com.masum.edu_portal.feature.home.data.posts.AllPostsResponse
import com.masum.edu_portal.feature.home.data.posts.StatusResponse
import com.masum.edu_portal.feature.member.data.galleryinfo.GalleryInfoResponse
import com.masum.edu_portal.feature.member.data.memberinfolist.MemberInfoListResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {


    //comitee
    @POST(HTTP_PARAM.COMMITEE_LIST)
    fun getCommitteeMemberList(): Call<CommiteeMemberResponse>

    //Notifications
    @POST(HTTP_PARAM.NOTIFICATION)
    fun getNotifications(): Call<NotificationResponse>

    @POST(HTTP_PARAM.NOTIFICATION_POST)
    fun notificationCommentPost(@Body requestBody: RequestBody): Call<NotificaitonSaveResponse>


    //api for call member list
    @POST(HTTP_PARAM.MEMBER_LIST)
    fun getMemberListResponse(@Body requestBody: RequestBody): Call<MemberInfoListResponse>

    @POST(HTTP_PARAM.MEMBER_UPDATE)
    fun updateMemberResponse(
        @Body requestBody: RequestBody
    ): Call<CommiteeMemberResponse>

    @POST(HTTP_PARAM.MEMBER_INFO)
    fun getUserByID(
        @Body requestBody: RequestBody
    ): Call<CommiteeMemberResponse>

    @POST(HTTP_PARAM.DESIGNATION_LIST)
    fun getDesignationList(): Call<DesignationResponse>

    // All Post List
    @GET(HTTP_PARAM.STATUS_LIST)
    fun getAllPosts(): Call<AllPostsResponse>

    // Create Post
    @POST(HTTP_PARAM.STATUS_POST)
    fun createPost(
        @Body requestBody: RequestBody
    ): Call<StatusResponse>


    // All Gallery Images
    @POST(HTTP_PARAM.GALLERY_INFO)
    fun getGalleryImages(
        @Body requestBody: RequestBody?
    ): Call<GalleryInfoResponse?>?
    // Create Gallery


    // Delete Gallery
    @POST(HTTP_PARAM.GALLERY_DELETE)
    fun deleteGallery(
        @Body requestBody: RequestBody
    ): Call<GalleryInfoResponse>

    //about us
    @POST(HTTP_PARAM.ABOUT_US)
    fun getAboutUsData(): Call<AboutUsDataResponse>



}