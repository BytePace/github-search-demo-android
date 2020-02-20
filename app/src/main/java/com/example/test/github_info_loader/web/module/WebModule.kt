package com.example.test.github_info_loader.web.module

import com.example.test.github_info_loader.GH_REP_LINK
import com.example.test.github_info_loader.web.RepositoryInfoApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class WebModule {

    @Provides
    @Singleton
    fun infoLoaderRetrofitProvider() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(GH_REP_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun RepositoryInfoApi(retrofit: Retrofit) : RepositoryInfoApi {
        return retrofit.create(RepositoryInfoApi::class.java)
    }
}