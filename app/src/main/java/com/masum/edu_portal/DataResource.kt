package com.masum.edu_portal

class DataResource<T>(val status: AuthStatus, val data: T?, val message: String?) {

    enum class AuthStatus {
        SUCCESS, ERROR, LOADING
    }

    companion object {
       open fun <T> success(data: T): DataResource<T> {
            return DataResource(AuthStatus.SUCCESS, data, null)
        }

        open fun <T> error(msg: String): DataResource<T> {
            return DataResource(AuthStatus.ERROR, null, msg)
        }

        open fun <T> loading(): DataResource<T> {
            return DataResource(AuthStatus.LOADING, null, null)
        }


    }

}