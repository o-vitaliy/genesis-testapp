package com.genesis.testapp.data.repos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.genesis.testapp.data.repos.enities.RepoDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Query("SELECT * FROM repos LIMIT 20")
    fun getAll(): Flow<List<RepoDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: RepoDbEntity)
}
