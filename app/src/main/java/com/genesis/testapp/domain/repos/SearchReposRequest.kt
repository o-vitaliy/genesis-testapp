package com.genesis.testapp.domain.repos

data class SearchReposRequest(
    val query: String,
    val offset: Int,
    val limit: Int
)