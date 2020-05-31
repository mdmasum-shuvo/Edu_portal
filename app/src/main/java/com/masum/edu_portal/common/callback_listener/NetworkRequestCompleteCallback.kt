package com.masum.edu_portal.common.callback_listener

interface NetworkRequestCompleteCallback<T> {
    fun onRequestSuccess(data: T)
    fun onRequestFailed(errorText: String)
}