package com.ericho.example.http

import retrofit2.Retrofit

class RetrofitManager {


    companion object {
        private var retrofit: Retrofit? = null

        fun getRetrofit(): Retrofit {
            if (retrofit == null) {
                synchronized(RetrofitManager::class) {
                    if (retrofit == null) {
                        retrofit = Retrofit.Builder()
                            .baseUrl("https://api.github.com/")
                            .build()
                    }
                }
            }
            return retrofit!!
        }
    }
}