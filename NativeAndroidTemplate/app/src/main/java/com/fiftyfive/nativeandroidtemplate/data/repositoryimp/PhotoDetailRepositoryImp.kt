package com.fiftyfive.nativeandroidtemplate.data.repositoryimp

import com.fiftyfive.nativeandroidtemplate.business.Photo
import com.fiftyfive.nativeandroidtemplate.business.PhotoLocalRepository
import com.fiftyfive.nativeandroidtemplate.data.database.PhotoDetailsEntity
import com.fiftyfive.nativeandroidtemplate.data.database.PhotoListDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoDetailRepositoryImp @Inject constructor(private val photosDBDao: PhotoListDAO):
    PhotoLocalRepository {

    override suspend fun getPhotoListFromDatabase(): List<Photo> {
        return photosDBDao.getPhotos().map {
            Photo(it.id,
                it.author,
                it.width,
                it.height,
                it.url,
                it.downloadUrl,
                it.isLiked)
        }
    }

    override suspend fun addPhotosToDatabase(list: List<Photo>) {
        withContext(Dispatchers.IO) {
            photosDBDao.addPhotos(list.map {
                PhotoDetailsEntity(
                    it.id, it.author,
                    it.width,
                    it.height,
                    it.url,
                    it.downloadUrl,
                    false
                )
            })
        }
    }

    override suspend fun getPhotoDetail(id: String): Flow<Photo> {
        val photoDetail = photosDBDao.getPhotoDetail(id)
        return photoDetail.map {
            Photo(it.id, it.author, it.width, it.height, it.url, it.downloadUrl, it.isLiked)
        }
    }

    override suspend fun isFavorite(id: String): Boolean {
        return photosDBDao.getPhotoDetail(id).first().isLiked
    }

    override suspend fun addToFavorite(id: String): Int {
        return photosDBDao.addToFavorite(id)
    }

    override suspend fun removeFromFavorite(id: String): Int {
        return photosDBDao.deleteFromFavorite(id)
    }

}