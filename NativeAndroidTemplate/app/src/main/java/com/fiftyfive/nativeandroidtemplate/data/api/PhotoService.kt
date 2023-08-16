package com.fiftyfive.nativeandroidtemplate.data.api

import com.fiftyfive.nativeandroidtemplate.business.Photo
import retrofit2.http.GET

/**
 * Retrofit service interface for interacting with the remote API.
 */
interface PhotoService {
    /**
     *  Fetches the list of photos
     *  @return List of PhotoDetails
     */
    @GET("list")
    suspend fun getPhotosList() : List<Photo>
}