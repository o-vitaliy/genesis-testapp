package com.genesis.testapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.genesis.testapp.data.repos.dao.RepoDao
import com.genesis.testapp.data.repos.enities.RepoDbEntity

@Database(entities = [RepoDbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}