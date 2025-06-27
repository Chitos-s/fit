package com.example.fit.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fit.data.converters.ActivityTypeConverter
import com.example.fit.data.dao.UserActivityDao
import com.example.fit.model.UserActivity

@Database(
    entities = [UserActivity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ActivityTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userActivityDao(): UserActivityDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fefufit_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
} 