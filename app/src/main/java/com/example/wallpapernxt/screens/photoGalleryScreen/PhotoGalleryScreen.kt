package com.example.wallpapernxt.screens.photoGalleryScreen


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.wallpapernxt.data.DataOrException
import com.example.wallpapernxt.model.searchPhotos.searchPhotos
import com.example.wallpapernxt.navigation.PhotosAppScreens

@Composable
fun PhotoGalleryScreen(
    navController: NavController,
    photoGalleryScreenViewModel: PhotoGalleryScreenViewModel = hiltViewModel(),
    query: String?,
    page: Int = 1
) {
    val photosData = produceState<DataOrException<searchPhotos, Boolean, Exception>>(
        initialValue = DataOrException()
    ) {
        value = photoGalleryScreenViewModel.searchPhotos(query.toString(), page)
    }.value

    var isLoading by remember {
        mutableStateOf(true)
    }

    if (isLoading) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    }

    if (photosData != null) {
        isLoading = false
        photosData.data?.let { PhotoGrid(it,navController) }

    }

}

@Composable
fun PhotoGrid(data: searchPhotos,navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {

        for (i in 0..29){
            item {
                ImageCard(data, serialNumber = i, modifier = Modifier,navController)
            }
        }


    }

}

@Composable
fun ImageCard(
    data: searchPhotos,
    serialNumber: Int,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val path = data.results[serialNumber].id
    Log.d("photo_id", "ImageCard: $path")
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data.results[serialNumber].urls.regular)
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = Modifier
            .height(177.dp)
            .width(100.dp)
            .clickable {
                navController.navigate(PhotosAppScreens.PhotoViewScreen.name + "/$path")
            }

    )
}


