package com.ericho.example.data.repo

import com.ericho.example.data.INovelRepository

class NovelRepository(
    private val remoteRepository: INovelRepository,
    private val localRepository: INovelRepository
) : INovelRepository {
//    private val remoteRepository: INovelRepository by getScopeName().inject()
    override suspend fun getXXX(): Any {
        TODO("Not yet implemented")
    }


}