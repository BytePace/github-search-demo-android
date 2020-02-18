package com.example.test.github_info_loader.web

import com.example.test.github_info_loader.GH_REP_LINK
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GHInfoLoader {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(GH_REP_LINK)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}