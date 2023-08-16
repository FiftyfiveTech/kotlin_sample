package com.fiftyfive.nativeandroidtemplate.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 *  Room database to store PhotoDetailsEntity table
 */
@Database(entities = [PhotoDetailsEntity::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun photoListDao(): PhotoListDAO
}