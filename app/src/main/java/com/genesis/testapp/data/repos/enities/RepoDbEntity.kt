package com.genesis.testapp.data.repos.enities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class RepoDbEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "html_url") val url: String,
    @ColumnInfo(name = "language") val language: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "score") val score: Int
)