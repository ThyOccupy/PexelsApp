package com.example.pexelsapp.domain.usecase.interfaces

interface DownloadImageUseCase {

    suspend fun execute(url: String, id: Int) : Long

}