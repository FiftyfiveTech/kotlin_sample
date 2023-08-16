package com.fiftyfive.nativeandroidtemplate.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{
        // The base url to access the web API
        private const val BASE_URL = "https://picsum.photos/v2/"

        /**
         *  Creates a retrofit object
         *  specific to use PhotoService interface
         */
        fun getAPIService() : PhotoService {

            val client = OkHttpClient.Builder().build()
            val converter = GsonConverterFactory.create()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(converter).build()
                .create(PhotoService::class.java)
        }

    }
}