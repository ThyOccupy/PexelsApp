package com.example.pexelsapp.domain.usecase

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import com.example.pexelsapp.BuildConfig
import com.example.pexelsapp.domain.usecase.interfaces.DownloadImageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DownloadImageUseCaseImpl @Inject constructor(
    private val context: Context
) : DownloadImageUseCase {
    override suspend fun execute(url: String, id: Int): Long {
        return withContext(Dispatchers.IO) {
            val request = DownloadManager.Request(url.toUri())
                .setTitle("${id}.jpg")
                .setDescription("Downloading image with id $id")
                .setMimeType("image/jpeg")
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .addRequestHeader("Authorization", BuildConfig.API_KEY)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${id}.jpg")

            return@withContext context.getSystemService(DownloadManager::class.java)
                .enqueue(request)
        }
    }
}