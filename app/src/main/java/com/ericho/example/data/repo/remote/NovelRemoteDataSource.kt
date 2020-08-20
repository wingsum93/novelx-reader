package com.ericho.example.data.repo.remote

import com.ericho.example.data.INovelRepository
import com.ericho.example.http.NovelApi

class NovelRemoteDataSource(
    private val api: NovelApi
) : INovelRepository {


    override suspend fun getXXX(): Any {
        TODO("Not yet implemented")
    }

}