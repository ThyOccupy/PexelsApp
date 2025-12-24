package com.example.pexelsapp.domain.usecase.interfaces

interface GetHeaderUseCase {
    suspend fun execute() : List<String>
}