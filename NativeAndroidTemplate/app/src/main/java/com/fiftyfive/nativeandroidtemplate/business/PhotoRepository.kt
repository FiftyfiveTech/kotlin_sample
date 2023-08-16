package com.fiftyfive.nativeandroidtemplate.business

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 *  interface to the data layer
 */
interface PhotoRepository {
    suspend fun getPhotos(): List<Photo>
}