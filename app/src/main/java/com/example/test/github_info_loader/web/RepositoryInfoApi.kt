package com.example.test.github_info_loader.web

import com.example.test.github_info_loader.core.model.RepositoryInfoList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RepositoryInfoApi {
    @GET("search/repositories")
    fun repositoryList(@Query("q") keyWord: String,
                       @Query("page") pageNum: Int,
                       @Query("per_page") sizePage: Int) : Call<RepositoryInfoList>
}