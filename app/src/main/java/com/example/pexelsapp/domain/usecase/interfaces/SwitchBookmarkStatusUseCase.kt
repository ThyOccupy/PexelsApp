package com.example.pexelsapp.domain.usecase.interfaces

interface SwitchBookmarkStatusUseCase {
    suspend fun execute(id: Int)
}