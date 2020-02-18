package com.example.test.github_info_loader.core.model

import com.google.gson.annotations.SerializedName

data class RepositoryOwner(
    @SerializedName("login")
    val userName: String
) {

}