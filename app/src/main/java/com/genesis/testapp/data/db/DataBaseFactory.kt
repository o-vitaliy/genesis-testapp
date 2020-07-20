package com.genesis.testapp.data.db

import android.content.Context
import androidx.room.Room

object DataBaseFactory {
    fun create(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-1"
    ).build()
}
