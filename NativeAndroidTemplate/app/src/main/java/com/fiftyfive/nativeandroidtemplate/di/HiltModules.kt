package com.fiftyfive.nativeandroidtemplate.di

import android.content.Context
import androidx.room.Room
import com.fiftyfive.nativeandroidtemplate.business.PhotoLocalRepository
import com.fiftyfive.nativeandroidtemplate.business.PhotoRepository
import com.fiftyfive.nativeandroidtemplate.data.repositoryimp.PhotoDetailRepositoryImp
import com.fiftyfive.nativeandroidtemplate.data.api.ApiClient
import com.fiftyfive.nativeandroidtemplate.data.api.PhotoService
import com.fiftyfive.nativeandroidtemplate.data.database.PhotoDatabase
import com.fiftyfive.nativeandroidtemplate.data.database.PhotoListDAO
import com.fiftyfive.nativeandroidtemplate.data.repositoryimp.PhotoRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class HiltModules {

    /**
     *  Provides Retrofit APIs service implementation
     *  @return APIService instance to make network calls
     */
    @Provides
    fun providesProductService(): PhotoService = ApiClient.getAPIService()

    /**
     *  Provides PhotoRepositoryImp
     *  @param service instance of PhotoService interface
     *  @return PhotoRepositoryImp object which inherits PhotoRepository
     */
    @Provides
    fun providesPhotoRepositoryAPI(
        service: PhotoService
    ): PhotoRepositoryImp = PhotoRepositoryImp(service)

    /**
     *  Provides PhotoRepository
     *  @param photoRepositoryImp instance of photoRepositoryImp
     *  @return PhotoRepository
     */
    @Provides
    fun providesPhotoRepository(
        photoRepositoryImp: PhotoRepositoryImp
    ): PhotoRepository = photoRepositoryImp

    /**
     *  Provides PhotoLocalRepository object
     *  @param photoRepositoryImp instance of photoRepositoryImp
     *  @return PhotoRepository
     */
    @Provides
    fun providesPhotoLocalRepository(
        photoDetailRepositoryImp: PhotoDetailRepositoryImp
    ): PhotoLocalRepository = photoDetailRepositoryImp

    /**
     *  Provides PhotoDetailRepositoryImp object
     *  @param photoListDAO instance of PhotoListDAO
     *  @return PhotoDetailRepositoryImp
     */
    @Provides
    fun providesPhotoDetailRepositoryImp(
        photoListDAO: PhotoListDAO
    ): PhotoDetailRepositoryImp = PhotoDetailRepositoryImp(photoListDAO)

    /**
     * Hilt creates a singleton
     * accessible everywhere
     */
    @Singleton
    @Provides
    fun providePhotoDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        PhotoDatabase::class.java,
        "photodb"
    ).build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun providePhotoDao(db: PhotoDatabase) = db.photoListDao()
}