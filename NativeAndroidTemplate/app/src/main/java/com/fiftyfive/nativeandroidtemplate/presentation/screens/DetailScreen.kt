package com.fiftyfive.nativeandroidtemplate.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.fiftyfive.nativeandroidtemplate.business.Photo
import com.fiftyfive.nativeandroidtemplate.presentation.common.CustomLayoutTextView
import com.fiftyfive.nativeandroidtemplate.presentation.common.ErrorScreen
import com.fiftyfive.nativeandroidtemplate.presentation.viewmodels.PhotoDetailViewModel
import com.fiftyfive.nativeandroidtemplate.presentation.viewstates.PhotoDetailViewState

@Composable
fun DetailScreen(photoDetailViewModel: PhotoDetailViewModel
){
    val collectAsState = photoDetailViewModel.photoDetailState.collectAsState()
    when(val result = collectAsState.value){
        is PhotoDetailViewState.Loading ->{
            ErrorScreen(message = "Loading..")
        }
        is PhotoDetailViewState.Success ->{
            DetailView(result.detail)
        }
        else -> {}
    }
}

@Composable
fun DetailView(photo: Photo) {
    Surface(modifier = Modifier
        .background(MaterialTheme.colorScheme.inversePrimary)
    ) {
        Column {
            AsyncImage(
                model = photo.downloadUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
            Column(modifier = Modifier.weight(1f)) {
                CustomLayoutTextView(text1 = "Author", photo.author)
                Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(MaterialTheme.colorScheme.inversePrimary))
                CustomLayoutTextView(text1 = "Width", photo.width.toString())
                Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(MaterialTheme.colorScheme.inversePrimary))
                CustomLayoutTextView(text1 = "Height", photo.height.toString())
            }
        }
    }

}
