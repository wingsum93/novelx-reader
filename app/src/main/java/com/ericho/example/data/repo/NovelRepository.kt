package com.ericho.example.data.repo

import com.ericho.example.data.INovelRepository

class NovelRepository(
    private val remoteRepository: INovelRepository,
    private val localRepository: INovelRepository
) : INovelRepository {

    override suspend fun getXXX(): Any {
        TODO("Not yet implemented")
    }


}