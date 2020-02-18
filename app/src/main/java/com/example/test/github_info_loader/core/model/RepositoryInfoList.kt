package com.example.test.github_info_loader.core.model

import com.google.gson.annotations.SerializedName

data class RepositoryInfoList(
    @SerializedName("total_count")
    var totalCount: Int,
    @SerializedName("items")
    var list: List<SimpleRepositoryInfo>
) {
}