package com.example.wallpapernxt.screens.photoViewScreen

import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
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
import com.example.wallpapernxt.model.photoId.photoId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

@Composable
fun PhotoViewScreen(
    navController: NavController,
    photoViewScreenViewModel: PhotoViewScreenViewModel = hiltViewModel(),
    path:String?
) {
    val photoData = produceState<DataOrException<photoId, Boolean, Exception>>(
        initialValue = DataOrException()
    ) {
        value = photoViewScreenViewModel.viewPhoto(path = path.toString())
    }.value

    Log.d("Photodata", "PhotoViewScreen: $photoData")

    var isLoading by remember {
        mutableStateOf(true)
    }

    if (isLoading) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    }

    if (photoData != null) {
        isLoading = false
        photoData.data?.let { ViewPhotoScreen(data = it) }
    }
}
@Composable
fun ViewPhotoScreen(
    data:photoId
){


    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data.urls.regular)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.height(1200.dp)

        )

        //Wallpaper button

        val imgLink = data.urls.regular
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        val wallpaperManager = WallpaperManager.getInstance(context)

        OutlinedButton(onClick = {

            try {
                coroutineScope.launch {
                    val task = async(Dispatchers.IO) {
                        BitmapFactory.decodeStream(
                            URL(imgLink).openConnection().getInputStream()
                        )
                    }
                    val bitmap = task.await()
                    wallpaperManager.setBitmap(
                        bitmap,
                        null,
                        false,
                        WallpaperManager.FLAG_SYSTEM
                    )
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            Toast.makeText(context,"Wallpaper set successfully", Toast.LENGTH_SHORT).show()

        }, modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "Set as home screen wallpaper")
        }
    }
}