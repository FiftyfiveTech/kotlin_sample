package com.fiftyfive.nativeandroidtemplate.business

import kotlinx.coroutines.flow.Flow

/**
 *  interface to the data layer,
 *  defines all the methods for local database operations
 */
interface PhotoLocalRepository {
    suspend fun getPhotoListFromDatabase() : List<Photo>
    suspend fun addPhotosToDatabase(list :List<Photo>)
    suspend fun getPhotoDetail(id :String) : Flow<Photo>
    suspend fun isFavorite(id: String) : Boolean
    suspend fun addToFavorite(id: String): Int
    suspend fun removeFromFavorite(id: String): Int
}