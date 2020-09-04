package com.ericho.example

import com.ericho.example.data.INovelRepository
import com.ericho.example.data.repo.NovelRepository
import com.ericho.example.data.repo.remote.NovelRemoteDataSource
import com.ericho.example.ext.DocumentHelper
import com.ericho.example.http.NovelApi
import com.ericho.example.http.OkManager
import com.ericho.example.http.RetrofitManager
import com.ericho.example.ui.main.MainViewModel
import com.ericho.example.ui.novel.NovelViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    // view model
    viewModel<MainViewModel>()
    viewModel<NovelViewModel>()


    //data
    factory<INovelRepository>(StringQualifier("remote")) { NovelRemoteDataSource(get(), get()) }
    factory<INovelRepository>(StringQualifier("local")) { NovelRemoteDataSource(get(), get()) }

    factory<INovelRepository> {
        NovelRepository(
            remoteRepository = get(StringQualifier("remote")),
            localRepository = get(StringQualifier("local"))
        )
    }

    factory { DocumentHelper() }
    factory<NovelApi> { get<Retrofit>().create(NovelApi::class.java) }

    factory<OkHttpClient> { OkManager.getClient() }

    factory { RetrofitManager.getRetrofit() }

}