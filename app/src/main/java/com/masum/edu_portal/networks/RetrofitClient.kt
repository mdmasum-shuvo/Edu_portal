package com.masum.edu_portal.networks

import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.masum.edu_portal.BuildConfig
import com.masum.edu_portal.utils.QueryParameterAddInterceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

object RetrofitClient{
     var retrofit: Retrofit? = null
     val gson = GsonBuilder().setLenient().create()
    /**
     * API service object
     *
     * @return ApiService to call the API's
     */
    fun callRetrofit(): ApiService {

        return client.create<ApiService>(ApiService::class.java)
    }

    val client: Retrofit
        get() {
            if (RetrofitClient.retrofit == null) {
                synchronized(Retrofit::class.java) {
                    if (RetrofitClient.retrofit == null) {

                        val httpClient = OkHttpClient.Builder()
                            .addInterceptor(QueryParameterAddInterceptor())

                        // for pretty log of HTTP request-response
                        httpClient.addInterceptor(
                            LoggingInterceptor.Builder()
                                .loggable(BuildConfig.DEBUG)
                                .setLevel(Level.BASIC)
                                .log(Platform.INFO)
                                .request("LOG")
                                .response("LOG")
                                .executor(Executors.newSingleThreadExecutor())
                                .build())

                        val client = httpClient.build()

                        RetrofitClient.retrofit = Retrofit.Builder()
                            .baseUrl(BuildConfig.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(RetrofitClient.gson))
                            .client(client)
                            .build()
                    }
                }

            }
            return RetrofitClient.retrofit!!
        }

    /**
     * Cancel all pending network calls in queue
     */


}