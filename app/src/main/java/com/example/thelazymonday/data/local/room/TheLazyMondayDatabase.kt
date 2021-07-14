package com.example.thelazymonday.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.thelazymonday.data.local.entity.GameNewsEntity
import java.security.AccessControlContext

@Database(entities = [GameNewsEntity::class], version = 1, exportSchema = false)
abstract class TheLazyMondayDatabase : RoomDatabase() {
    abstract fun theLazyMondayDao(): TheLazyMondayDbDao

    companion object {
        @Volatile
        private var INSTANCE: TheLazyMondayDatabase? = null

        fun getInstance(context: Context): TheLazyMondayDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    TheLazyMondayDatabase::class.java,
                    "theLazyMonday.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}