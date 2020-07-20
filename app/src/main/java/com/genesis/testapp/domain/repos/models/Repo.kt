package com.genesis.testapp.domain.repos.models

data class Repo(
    val id: Long,
    val name: String,
    val url: String,
    val language: String?,
    val description: String?,
    val stars: Int
)