package com.genesis.testapp.data.repos.enities

import com.google.gson.annotations.SerializedName

data class RepoEntity(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val url: String,
    @SerializedName("language") val language: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("stargazers_count") val stars: Int
)
