package com.masum.edu_portal.utils

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.masum.edu_portal.networks.ApiService
import com.masum.edu_portal.networks.RetrofitClient

open class NetworkUtils {

    companion object{
        open fun getApiService(): ApiService? {
            return RetrofitClient.callRetrofit()
        }

        open fun jsonFormat(src: Object):JsonObject{
            val gson = Gson()
            val jsonString = gson.toJson(src)
            val jsonObject = JsonParser().parse(jsonString).asJsonObject
            return jsonObject
        }
    }

}