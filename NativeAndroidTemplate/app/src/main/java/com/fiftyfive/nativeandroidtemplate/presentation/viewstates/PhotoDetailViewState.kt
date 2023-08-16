package com.fiftyfive.nativeandroidtemplate.presentation.viewstates

import com.fiftyfive.nativeandroidtemplate.business.Photo

/**
 *  View State for DetailScreen
 */
sealed class PhotoDetailViewState{
    object Loading : PhotoDetailViewState()
    class Success(val detail: Photo) : PhotoDetailViewState()
    object Error : PhotoDetailViewState()
}
