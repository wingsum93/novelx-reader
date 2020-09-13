package com.ericho.example.http

import okhttp3.OkHttpClient
import org.koin.java.KoinJavaComponent.get
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {


    companion object {
        private var retrofit: Retrofit? = null

        fun getRetrofit(): Retrofit {
            if (retrofit == null) {
                synchronized(RetrofitManager::class) {
                    if (retrofit == null) {
                        retrofit = Retrofit.Builder()
                            .baseUrl("https://api.github.com/")
                            .client(get(OkHttpClient::class.java))
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                    }
                }
            }
            return retrofit!!
        }
    }
}