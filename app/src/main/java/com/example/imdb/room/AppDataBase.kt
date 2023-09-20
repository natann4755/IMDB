package com.example.imdb.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.imdb.models.Movie

@Database(entities = [Movie::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouritesDao(): FavouritesDao

    companion object {
        private const val DATABASE_NAME = "app_database"

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .build()
        }
    }
}