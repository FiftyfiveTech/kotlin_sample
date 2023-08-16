package com.fiftyfive.nativeandroidtemplate.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.fiftyfive.nativeandroidtemplate.R
import com.fiftyfive.nativeandroidtemplate.business.Photo
import com.fiftyfive.nativeandroidtemplate.presentation.common.ErrorScreen
import com.fiftyfive.nativeandroidtemplate.presentation.viewmodels.PhotoListViewModel
import com.fiftyfive.nativeandroidtemplate.presentation.viewstates.PhotoListViewState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {

    val photoListViewModel: PhotoListViewModel = hiltViewModel()
    val collectAsState = photoListViewModel.photoListState.collectAsStateWithLifecycle()

    when(val result = collectAsState.value){
        is PhotoListViewState.Loading ->{
            ErrorScreen(stringResource(id = R.string.loading))
        }
        is PhotoListViewState.Success ->{
            SuccessView(result.data, navController,photoListViewModel)
        }
        is PhotoListViewState.Error ->{
            ErrorScreen(message = stringResource(id = R.string.error_screen))
        }

    }
}

@Composable
fun SuccessView(value: List<Photo>, navController: NavHostController,
                viewModel: PhotoListViewModel) {
    val configuration = LocalConfiguration.current
    Surface(modifier = Modifier
        .background(MaterialTheme.colorScheme.inversePrimary)
    ) {
        LazyColumn {
            items(value) {
                val hgt = configuration.screenWidthDp * 66 / 100
                val wid = configuration.screenWidthDp

                CardItem(
                    photo = it, wid.dp,
                    hgt.dp, navController, viewModel
                )
            }
        }
    }
}

@Composable
fun CardItem(
    photo: Photo,
    wid: Dp,
    hgt: Dp,
    navController: NavHostController,
    viewModel: PhotoListViewModel
) {
    Card(
        modifier = Modifier
            .width(wid)
            .height(hgt)
            .padding(10.dp)
    ) {
        val coroutineScope = rememberCoroutineScope()

        Box(
            modifier = Modifier.fillMaxSize()
                .clickable {
                    navController.navigate("detail_screen/"+photo.id)
                }) {
            AsyncImage(
                model = photo.downloadUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
            Row(modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.inversePrimary
                        )
                    )
                )
            ) {
                Text(
                    text = photo.author,
                    modifier = Modifier
                        .height(120.dp)
                        .weight(1f)
                        .wrapContentHeight(),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Image(
                    painterResource(
                        if (photo.isFavorite)
                            R.drawable.wish_select
                        else
                            R.drawable.wish_unselect

                    ),
                    contentDescription = "",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .width(36.dp)
                        .height(36.dp)
                        .padding(4.dp)
                        .clickable {
                            coroutineScope.launch {
                                viewModel.addOrRemoveFromFavorites(photo.id, !photo.isFavorite)
                            }
                        }
                )
            }

        }

    }
}
