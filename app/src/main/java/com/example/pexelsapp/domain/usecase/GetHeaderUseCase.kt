package com.example.pexelsapp.domain.usecase

interface GetHeaderUseCase {
    suspend fun execute() : List<String>
}