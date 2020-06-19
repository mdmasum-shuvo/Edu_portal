package com.masum.edu_portal.feature.auth.datamodel

class AuthResource<T>(val status: AuthStatus, val data: T?, val message: String?) {

    enum class AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
    }

    companion object {
       open fun <T> authenticated(data: T): AuthResource<T> {
            return AuthResource(AuthStatus.AUTHENTICATED, data, null)
        }

        open fun <T> error(msg: String): AuthResource<T> {
            return AuthResource(AuthStatus.ERROR, null, msg)
        }

        open fun <T> loading(): AuthResource<T> {
            return AuthResource(AuthStatus.LOADING, null, null)
        }

        open fun <T> logout(): AuthResource<T> {
            return AuthResource(AuthStatus.NOT_AUTHENTICATED, null, null)
        }
    }

}