package com.example.test.github_info_loader.core.model

import com.google.gson.annotations.SerializedName

data class SimpleRepositoryInfo(
    @SerializedName("name")
    val repositoryName: String,
    @SerializedName("url")
    val repositoryURL: String,
    @SerializedName("owner")
    val repositoryOwner: RepositoryOwner
) {
}