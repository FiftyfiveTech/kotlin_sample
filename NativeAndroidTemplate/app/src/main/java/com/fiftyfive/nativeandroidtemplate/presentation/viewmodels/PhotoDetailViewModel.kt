package com.fiftyfive.nativeandroidtemplate.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiftyfive.nativeandroidtemplate.business.PhotoLocalRepository
import com.fiftyfive.nativeandroidtemplate.presentation.viewstates.PhotoDetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *  Viewmodel for DetailScreen
 *  @param repository instance of PhotoLocalRepository
 */
@HiltViewModel
class PhotoDetailViewModel @Inject constructor(private val repository: PhotoLocalRepository) : ViewModel() {

    val photoDetailState : MutableStateFlow<PhotoDetailViewState> = MutableStateFlow(PhotoDetailViewState.Loading)

    /**
     *  Fetch details of photo on the basis of its id
     *  and manages view state
     *  @param id the unique id of photo
     */
    fun fetchDetail(id: String){

        viewModelScope.launch(Dispatchers.IO) {
            photoDetailState.value = PhotoDetailViewState.Loading
            val photoDetail = repository.getPhotoDetail(id)
            photoDetail.collect{
                photoDetailState.value = PhotoDetailViewState.Success(it)
            }
        }
    }

}