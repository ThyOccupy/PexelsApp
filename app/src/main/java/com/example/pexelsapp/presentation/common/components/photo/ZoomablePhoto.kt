package com.example.pexelsapp.presentation.common.components.photo

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import com.example.pexelsapp.presentation.model.PhotoUiEntity

@Composable
fun ColumnScope.ZoomablePhoto(
    photo: PhotoUiEntity,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(getSizeModifier(photo))
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context)
                .data(photo.url)
                .scale(Scale.FIT)
                .crossfade(true)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build()
        )

        ZoomableImage(
            painter = painter,
            imageAlign = Alignment.Center,
            contentScale = ContentScale.Crop,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@SuppressLint("ModifierFactoryExtensionFunction")
private fun ColumnScope.getSizeModifier(photo: PhotoUiEntity): Modifier {
    return if (photo.width == 0 || photo.height == 0) {
        Modifier.weight(1f)
    } else {
        val aspectRatio = countSize(photo.width, photo.height)
        Modifier.aspectRatio(ratio = aspectRatio, matchHeightConstraintsFirst = false)
    }
}