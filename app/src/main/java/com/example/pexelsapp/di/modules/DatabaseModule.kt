package com.example.pexelsapp.di.modules

import android.app.Application
import androidx.room.Room
import com.example.pexelsapp.data.db.AppDatabase
import com.example.pexelsapp.data.db.dao.FeaturedCollectionDao
import com.example.pexelsapp.data.db.dao.PhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "pexels_app_database"
        ).build()

    @Provides
    fun providePhotoDao(db: AppDatabase): PhotoDao =
        db.photoDao()

    @Provides
    fun provideFeaturedCollectionDao(db: AppDatabase): FeaturedCollectionDao =
        db.featuredCollectionDao()
}