package com.masum.edu_portal.feature.home.callbackListener


interface HomeCallBackListener {
    fun memberList(pageNumber :Int)
    fun aboutDataList()
    fun memberStatus(pageNumber: Int)
    fun error()
    fun progress()
}