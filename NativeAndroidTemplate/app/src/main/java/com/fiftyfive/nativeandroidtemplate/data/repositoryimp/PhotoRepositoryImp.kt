package com.fiftyfive.nativeandroidtemplate.data.repositoryimp

import com.fiftyfive.nativeandroidtemplate.business.Photo
import com.fiftyfive.nativeandroidtemplate.business.PhotoRepository
import com.fiftyfive.nativeandroidtemplate.data.api.PhotoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoRepositoryImp constructor(private val service: PhotoService): PhotoRepository {

    override suspend fun getPhotos(): List<Photo>{
        return withContext(Dispatchers.Main) {
            service.getPhotosList()
        }
    }

}