package com.fiftyfive.nativeandroidtemplate.presentation.viewstates

import com.fiftyfive.nativeandroidtemplate.business.Photo

/**
 *  View State for HomeScreen
 */
sealed class PhotoListViewState{
    object Loading : PhotoListViewState()
    data class Success(val data: List<Photo>) : PhotoListViewState()
    object Error : PhotoListViewState()
}

/**
 *  Updates whether the photo is favorite or not on tap of icon
 *  @param photoID The unique id of photo
 *  @param isFavorite if favorite then true else false
 */
fun PhotoListViewState.Success.updateFavorite(
    photoID: String,
    isFavorite: Boolean
) : PhotoListViewState.Success {
    return PhotoListViewState.Success(data = this.data.map { photo ->
        if (photo.id == photoID)
            photo.copy(isFavorite = isFavorite)
        else
            photo
    })
}
