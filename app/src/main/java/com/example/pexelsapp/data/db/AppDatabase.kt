package com.example.pexelsapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pexelsapp.data.db.dao.FeaturedCollectionDao
import com.example.pexelsapp.data.db.dao.PhotoDao
import com.example.pexelsapp.data.db.entities.FeaturedCollectionEntity
import com.example.pexelsapp.data.db.entities.PhotoEntity



@Database(
    entities = [PhotoEntity::class, FeaturedCollectionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract class photoDao: PhotoDao
    abstract class featuredCollectionDao: FeaturedCollectionDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDatabase::class.java,
                    name = "pexels_app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}