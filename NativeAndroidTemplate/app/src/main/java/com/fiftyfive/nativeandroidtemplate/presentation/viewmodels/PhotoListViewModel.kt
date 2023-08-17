package com.fiftyfive.nativeandroidtemplate.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiftyfive.nativeandroidtemplate.business.PhotoLocalRepository
import com.fiftyfive.nativeandroidtemplate.business.PhotoRepository
import com.fiftyfive.nativeandroidtemplate.di.HiltModules
import com.fiftyfive.nativeandroidtemplate.presentation.viewstates.PhotoListViewState
import com.fiftyfive.nativeandroidtemplate.presentation.viewstates.updateFavorite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *  Viewmodel for HomeScreen
 *  @param repository instance of PhotoRepository interface from domain layer
 *  @param dbRepository instance of PhotoLocalRepository interface from domain layer
 */
@HiltViewModel
class PhotoListViewModel @OptIn(ExperimentalCoroutinesApi::class)
@Inject constructor(private val repository: PhotoRepository,
                    private val dbRepository: PhotoLocalRepository,
                    @HiltModules.IsNetworkAvailable val isConnected: Boolean) : ViewModel() {

    val photoListState : MutableStateFlow<PhotoListViewState> = MutableStateFlow(PhotoListViewState.Loading)

    init {
        fetchImages()
    }

    /**
     *  Fetches images from web api using retrofit call
     *  and saves the fetched photos to the local database
     *  also manages view state of home screen
     */
    private fun fetchImages(){

        viewModelScope.launch(Dispatchers.IO) {
            photoListState.value = PhotoListViewState.Loading

            if (isConnected) {
                val photoList = repository.getPhotos()
                if (photoList.isNotEmpty()) {
                    dbRepository.addPhotosToDatabase(photoList)
                    photoListState.value = PhotoListViewState.Success(dbRepository.getPhotoListFromDatabase())
                }
                else
                    photoListState.value = PhotoListViewState.Error
            }
            else{
                val photos = dbRepository.getPhotoListFromDatabase()
                if (photos.isEmpty())
                    photoListState.value = PhotoListViewState.Error
                else
                    photoListState.value = PhotoListViewState.Success(photos)
            }
        }

    }

    /**
     *  on tap of photo favorite icon, if isLiked is true then adds to favorite
     *  else removes from favorite
     *  @param id the unique id of photo
     *  @param isLiked true if is favorite
     */
    suspend fun addOrRemoveFromFavorites(id: String, isLiked: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            if (dbRepository.isFavorite(id))
                dbRepository.removeFromFavorite(id)
            else
                dbRepository.addToFavorite(id)

            (photoListState.value as? PhotoListViewState.Success)?.let {
                photoListState.value =it.updateFavorite(id, isLiked)
            }
        }

    }
}